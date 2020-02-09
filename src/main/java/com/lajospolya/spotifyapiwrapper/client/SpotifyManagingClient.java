package com.lajospolya.spotifyapiwrapper.client;

import com.google.gson.Gson;
import com.lajospolya.spotifyapiwrapper.authorization.AuthorizationResponse;
import com.lajospolya.spotifyapiwrapper.spotifyexception.SpotifyErrorContainer;
import com.lajospolya.spotifyapiwrapper.spotifyexception.SpotifyRequestAuthorizationException;
import com.lajospolya.spotifyapiwrapper.spotifyexception.SpotifyRequestBuilderException;
import com.lajospolya.spotifyapiwrapper.spotifyexception.SpotifyResponseException;
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
            throws SpotifyRequestAuthorizationException, SpotifyRequestBuilderException, SpotifyResponseException
    {
        try
        {
            setAccessTokenOfRequest(spotifyRequest);
            HttpRequest request = buildRequest(spotifyRequest);

            Type genericType = getGenericTypeOfRequest(spotifyRequest);

            return sendRequestAndFetchResponse(request, genericType);
        }
        catch (NoSuchMethodException | InvocationTargetException e)
        {
            throw new SpotifyRequestBuilderException("Unable to build the request");
        }
        catch (NoSuchFieldException | IllegalAccessException e)
        {
            throw new SpotifyRequestAuthorizationException("Unable to set the access token");
        }
        catch (InterruptedException | IOException e)
        {
            throw new SpotifyResponseException("An exception occurred while sending the request");
        }
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

    private <T> T sendRequestAndFetchResponse(HttpRequest request, Type typeOfReturnValue) throws IOException, InterruptedException, SpotifyResponseException
    {
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        validateResponse(response);
        String body = response.body();

        if(isStringType(typeOfReturnValue))
        {
            return (T) body;
        }
        return gson.fromJson(body, typeOfReturnValue);
    }

    private void validateResponse(HttpResponse<String> response) throws SpotifyResponseException
    {
        int statusCode = response.statusCode();
        if(isClientErrorStatusCode(statusCode) || isServerErrorStatusCode(statusCode))
        {
            SpotifyErrorContainer error = gson.fromJson(response.body(), SpotifyErrorContainer.class);
            throw new SpotifyResponseException(error);
        }
    }

    private Boolean isClientErrorStatusCode(int statusCode)
    {
        return statusCode / 100 == 4;
    }

    private Boolean isServerErrorStatusCode(int statusCode)
    {
        return statusCode / 100 == 5;
    }

    private Boolean isStringType(Type typeOfReturnValue)
    {
        return String.class.getTypeName().equals(typeOfReturnValue.getTypeName());
    }

    private <T> Type getGenericTypeOfRequest(SpotifyRequest<T> spotifyRequest)
    {

        return ((ParameterizedType)spotifyRequest.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}
