package com.lajospolya.spotifyapiwrapper.client;

import com.lajospolya.spotifyapiwrapper.client.service.ISpotifyApiClientService;
import com.lajospolya.spotifyapiwrapper.client.service.SpotifyApiClientService;
import com.lajospolya.spotifyapiwrapper.reflection.IReflectiveSpotifyClientService;
import com.lajospolya.spotifyapiwrapper.reflection.ReflectiveSpotifyClientService;
import com.lajospolya.spotifyapiwrapper.response.ClientCredentialsFlowResponse;
import com.lajospolya.spotifyapiwrapper.spotifyexception.SpotifyRequestAuthorizationException;
import com.lajospolya.spotifyapiwrapper.spotifyexception.SpotifyRequestBuilderException;
import com.lajospolya.spotifyapiwrapper.spotifyexception.SpotifyResponseException;
import com.lajospolya.spotifyapiwrapper.spotifyrequest.AbstractSpotifyRequest;
import com.lajospolya.spotifyapiwrapper.spotifyrequest.AuthorizationCodeFlow;
import com.lajospolya.spotifyapiwrapper.spotifyrequest.ClientCredentialsFlow;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.net.http.HttpRequest;

public class SpotifyApiClient
{
    // This is the base class but we need better impl for this
    private ClientCredentialsFlowResponse apiTokenResponse;
    private String builtToken;

    private Long timeOfAuthorization;
    private IReflectiveSpotifyClientService reflectiveSpotifyClientService;
    private ISpotifyApiClientService spotifyApiClientService;

    private static final String BASIC_AUTHORIZATION = "Basic ";

    public static SpotifyApiClient createClientCredentialsFlowClient(String clientId, String clientSecret) throws SpotifyRequestAuthorizationException
    {
        return new SpotifyApiClient(clientId, clientSecret);
    }

    /*
    Have a generic re-authenticate which will either use refresh token (Authorization Flow)
    or not (Client Credentials)
     */
    public static SpotifyApiClient createAuthorizationFlowClient(String clientId, String clientSecret, String code, String redirectUri) throws SpotifyRequestAuthorizationException
    {
        UriComponentsBuilder redirectUriBuilder =  UriComponentsBuilder.fromUriString(redirectUri);
        return new SpotifyApiClient(clientId, clientSecret, code, redirectUriBuilder);
    }

    // Constructor for testing
    SpotifyApiClient(IReflectiveSpotifyClientService reflectiveSpotifyClientService,
                     ISpotifyApiClientService spotifyApiClientService, ClientCredentialsFlowResponse apiTokenResponse)
    {
        this.reflectiveSpotifyClientService = reflectiveSpotifyClientService;
        this.spotifyApiClientService = spotifyApiClientService;
        // This is the base class but we need better impl for this
        // since we're losing the refresh token
        this.apiTokenResponse = apiTokenResponse;
    }

    private SpotifyApiClient()
    {
        this.reflectiveSpotifyClientService = new ReflectiveSpotifyClientService();
        this.spotifyApiClientService = new SpotifyApiClientService();
        this.timeOfAuthorization = System.currentTimeMillis();
    }

    private SpotifyApiClient(String clientId, String clientSecret, String code, UriComponentsBuilder uriBuilder) throws SpotifyRequestAuthorizationException
    {
        this();

        AuthorizationCodeFlow authorizedClient = new AuthorizationCodeFlow.Builder(code, uriBuilder.build().toUriString()).build();
        String base64EncodedAuthKey = spotifyApiClientService.getBase64EncodedAuthorizationKey(clientId, clientSecret);

        // This is the base class but we need better impl for this
        // since we're losing the refresh token
        this.apiTokenResponse = sendRequest(authorizedClient, BASIC_AUTHORIZATION + base64EncodedAuthKey);
        this.builtToken = apiTokenResponse.getTokenType() + " " + apiTokenResponse.getAccessToken();
    }

    private SpotifyApiClient(String clientId, String clientSecret) throws SpotifyRequestAuthorizationException
    {
        this();

        ClientCredentialsFlow authorizedClient = new ClientCredentialsFlow.Builder().build();
        String base64EncodedAuthKey = spotifyApiClientService.getBase64EncodedAuthorizationKey(clientId, clientSecret);

        // This is the base class but we need better impl for this
        // since we're losing the refresh token
        this.apiTokenResponse = sendRequest(authorizedClient, BASIC_AUTHORIZATION + base64EncodedAuthKey);
        this.builtToken = apiTokenResponse.getTokenType() + " " + apiTokenResponse.getAccessToken();
    }

    public <T> T sendRequest(AbstractSpotifyRequest<T> spotifyRequest)
            throws SpotifyRequestAuthorizationException, SpotifyRequestBuilderException, SpotifyResponseException
    {
        if(spotifyApiClientService.hasTokenExpired(this.timeOfAuthorization, this.apiTokenResponse.getExpiresIn()))
        {
            throw new SpotifyRequestAuthorizationException("Access Token Has Expired");
        }

        return sendRequest(spotifyRequest, this.builtToken);
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
        catch (InvocationTargetException | IllegalAccessException e)
        {
            throw new SpotifyRequestBuilderException("Unable to build the request");
        }
        catch (InterruptedException | IOException e)
        {
            throw new SpotifyResponseException("An exception occurred while sending the request");
        }
    }
}
