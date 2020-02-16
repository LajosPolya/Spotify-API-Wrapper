package com.lajospolya.spotifyapiwrapper.client;

import com.lajospolya.spotifyapiwrapper.reflection.IReflectiveSpotifyClientService;
import com.lajospolya.spotifyapiwrapper.reflection.ReflectiveSpotifyClientService;
import com.lajospolya.spotifyapiwrapper.response.AuthorizationResponse;
import com.lajospolya.spotifyapiwrapper.spotifyexception.SpotifyRequestAuthorizationException;
import com.lajospolya.spotifyapiwrapper.spotifyexception.SpotifyRequestBuilderException;
import com.lajospolya.spotifyapiwrapper.spotifyexception.SpotifyResponseException;
import com.lajospolya.spotifyapiwrapper.spotifyrequest.AbstractSpotifyRequest;
import com.lajospolya.spotifyapiwrapper.spotifyrequest.ClientCredentialsFlow;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.net.http.HttpRequest;

public class SpotifyApiClient
{
    private AuthorizationResponse apiTokenResponse;
    private String builtToken;

    private Long timeOfAuthorization;
    private IReflectiveSpotifyClientService reflectiveSpotifyClientService;
    private ISpotifyApiClientService spotifyApiClientService;

    private static final String BASIC_AUTHORIZATION = "Basic ";

    public static SpotifyApiClient createClientCredentialsAuthorizedClient(String clientId, String clientSecret) throws SpotifyRequestAuthorizationException
    {
        return new SpotifyApiClient(clientId, clientSecret);
    }

    private SpotifyApiClient(){}

    private SpotifyApiClient(String clientId, String clientSecret) throws SpotifyRequestAuthorizationException
    {
        this.reflectiveSpotifyClientService = new ReflectiveSpotifyClientService();
        this.spotifyApiClientService = new SpotifyApiClientService();

        ClientCredentialsFlow authorizedClient = new ClientCredentialsFlow.Builder().build();
        String base64EncodedAuthKey = spotifyApiClientService.getBase64EncodedAuthorizationKey(clientId, clientSecret);
        this.timeOfAuthorization = System.currentTimeMillis();
        AuthorizationResponse authResponse = sendRequest(authorizedClient, BASIC_AUTHORIZATION + base64EncodedAuthKey);

        this.apiTokenResponse = authResponse;
        this.builtToken = apiTokenResponse.getTokenType() + " " + apiTokenResponse.getAccessToken();
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
            this.reflectiveSpotifyClientService.setAccessTokenOfRequest(spotifyRequest, accessToken);

            HttpRequest request = this.reflectiveSpotifyClientService.buildRequest(spotifyRequest);

            Type genericType = this.reflectiveSpotifyClientService.getGenericTypeOfRequest(spotifyRequest);

            return spotifyApiClientService.sendRequestAndFetchResponse(request, genericType);
        }
        catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e)
        {
            throw new SpotifyRequestBuilderException("Unable to build the request");
        }
        catch (InterruptedException | IOException e)
        {
            throw new SpotifyResponseException("An exception occurred while sending the request");
        }
    }
}
