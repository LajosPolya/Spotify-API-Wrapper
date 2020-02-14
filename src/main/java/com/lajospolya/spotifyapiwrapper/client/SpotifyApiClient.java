package com.lajospolya.spotifyapiwrapper.client;

import com.google.gson.Gson;
import com.lajospolya.spotifyapiwrapper.response.AuthorizationResponse;
import com.lajospolya.spotifyapiwrapper.reflection.IReflectiveSpotifyClientService;
import com.lajospolya.spotifyapiwrapper.reflection.ReflectiveSpotifyClientService;
import com.lajospolya.spotifyapiwrapper.spotifyexception.SpotifyErrorContainer;
import com.lajospolya.spotifyapiwrapper.spotifyexception.SpotifyRequestAuthorizationException;
import com.lajospolya.spotifyapiwrapper.spotifyexception.SpotifyRequestBuilderException;
import com.lajospolya.spotifyapiwrapper.spotifyexception.SpotifyResponseException;
import com.lajospolya.spotifyapiwrapper.spotifyrequest.AbstractSpotifyRequest;
import com.lajospolya.spotifyapiwrapper.spotifyrequest.ClientCredentialsFlow;

import java.io.IOException;
import java.lang.reflect.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class SpotifyApiClient
{
    private HttpClient httpClient;
    private AuthorizationResponse apiTokenResponse;
    private String builtToken;

    private Gson gson;
    private Long timeOfAuthorization;
    private IReflectiveSpotifyClientService reflectiveSpotifyClientService;

    private static final String BASIC_AUTHORIZATION = "Basic ";

    public static SpotifyApiClient createClientCredentialsAuthorizedClient(String clientId, String clientSecret) throws SpotifyRequestAuthorizationException
    {
        return new SpotifyApiClient(clientId, clientSecret);
    }

    private SpotifyApiClient(){}

    private SpotifyApiClient(String clientId, String clientSecret) throws SpotifyRequestAuthorizationException
    {
        this.httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();
        this.gson =  new Gson();
        this.reflectiveSpotifyClientService = new ReflectiveSpotifyClientService();

        ClientCredentialsFlow authorizedClient = new ClientCredentialsFlow.Builder().build();
        String base64EncodedAuthKey = getBase64EncodedAuthorizationKey(clientId, clientSecret);
        this.timeOfAuthorization = System.currentTimeMillis();
        AuthorizationResponse authResponse = sendRequest(authorizedClient, BASIC_AUTHORIZATION + base64EncodedAuthKey);

        this.apiTokenResponse = authResponse;
        this.builtToken = apiTokenResponse.getTokenType() + " " + apiTokenResponse.getAccessToken();
    }

    private static String getBase64EncodedAuthorizationKey(String clientId, String clientSecret)
    {
        byte[] authorizationKey = (clientId + ":" + clientSecret).getBytes(StandardCharsets.UTF_8);
        return Base64.getEncoder().encodeToString(authorizationKey);
    }

    public <T> T sendRequest(AbstractSpotifyRequest<T> spotifyRequest)
            throws SpotifyRequestAuthorizationException, SpotifyRequestBuilderException, SpotifyResponseException
    {
        if(hasTokenExpired())
        {
            throw new SpotifyRequestAuthorizationException("Access Token Has Expired");
        }

        return sendRequest(spotifyRequest, this.builtToken);
    }

    private Boolean hasTokenExpired()
    {
        return (System.currentTimeMillis() - this.timeOfAuthorization) / 1000L > this.apiTokenResponse.getExpiresIn();
    }

    private <T> T sendRequest(AbstractSpotifyRequest<T> spotifyRequest, String accessToken)
            throws SpotifyRequestAuthorizationException, SpotifyRequestBuilderException, SpotifyResponseException
    {
        try
        {
            /* set access token should set the header since
        each .header adds a new header

        .setHeader just overwrites it but it only had to be done once
         */
            this.reflectiveSpotifyClientService.setAccessTokenOfRequest(spotifyRequest, accessToken);

            HttpRequest request = this.reflectiveSpotifyClientService.buildRequest(spotifyRequest);

            Type genericType = this.reflectiveSpotifyClientService.getGenericTypeOfRequest(spotifyRequest);

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
}
