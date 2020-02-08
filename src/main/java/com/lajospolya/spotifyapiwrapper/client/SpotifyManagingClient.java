package com.lajospolya.spotifyapiwrapper.client;

import com.google.gson.Gson;
import com.lajospolya.spotifyapiwrapper.authorization.AuthorizationResponse;
import com.lajospolya.spotifyapiwrapper.spotifyrequest.SpotifyRequest;

import java.io.IOException;
import java.lang.reflect.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class SpotifyManagingClient
{
    private HttpClient httpClient;
    // Implement timeout with expires_in instead of waiting for 401
    private AuthorizationResponse apiTokenResponse;
    private String builtToken;
    private Gson gson;

    private static final String TOKEN_FIELD_NAME = "accessToken";
    private static final String BUILD_REQUEST_METHOD_NAME = "buildRequest";

    private SpotifyManagingClient(){}

    public SpotifyManagingClient(AuthorizationResponse authorizationResponse)
    {
        this.apiTokenResponse = authorizationResponse;
        this.httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();
        this.gson =  new Gson();
        this.builtToken = apiTokenResponse.getTokenType() + " " + apiTokenResponse.getAccessToken();
    }

    public <T> T sendRequest(SpotifyRequest<T> spotifyRequest)
    {
        try
        {
            setAccessTokenOfRequest(spotifyRequest);
            HttpRequest request = buildRequest(spotifyRequest);

            Type genericType = getGenericTypeOfRequest(spotifyRequest);

            return sendRequestAndFetchResponse(request, genericType);
        }
        catch (NoSuchFieldException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e)
        {
            System.out.println("Oops " + e.getMessage());
        }
        catch (InterruptedException | IOException e)
        {
            System.out.println("IO Error " + e.getMessage());
        }

        // Invalid Case, should instead rethrow an exception
        return null;
    }

    private <T> void setAccessTokenOfRequest(SpotifyRequest<T> spotifyRequest) throws NoSuchFieldException, IllegalAccessException
    {
        Class<?> runtimeRequest = spotifyRequest.getClass();
        Field accessToken = runtimeRequest.getDeclaredField(TOKEN_FIELD_NAME);
        accessToken.setAccessible(true);
        accessToken.set(spotifyRequest, this.builtToken);
    }

    private <T> HttpRequest buildRequest(SpotifyRequest<T> spotifyRequest) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException
    {
        Class<?> requestType = spotifyRequest.getClass();
        Method buildRequest = requestType.getDeclaredMethod(BUILD_REQUEST_METHOD_NAME, (Class<?>[]) null);

        buildRequest.setAccessible(true);
        return (HttpRequest) buildRequest.invoke(spotifyRequest, (Object[]) null);
    }

    private <T> T sendRequestAndFetchResponse(HttpRequest request, Type typeOfReturnValue) throws IOException, InterruptedException
    {
        HttpResponse<String> resp = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        String body = resp.body();

        if(String.class.getTypeName().equals(typeOfReturnValue.getTypeName()))
        {
            return (T) body;
        }
        return gson.fromJson(body, typeOfReturnValue);
    }

    private <T> Type getGenericTypeOfRequest(SpotifyRequest<T> spotifyRequest)
    {

        return ((ParameterizedType)spotifyRequest.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}
