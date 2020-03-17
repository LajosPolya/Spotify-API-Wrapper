package com.lajospolya.spotifyapiwrapper.client;

import com.lajospolya.spotifyapiwrapper.client.service.ISpotifyApiClientService;
import com.lajospolya.spotifyapiwrapper.client.service.SpotifyApiClientService;
import com.lajospolya.spotifyapiwrapper.reflection.IReflectiveSpotifyClientService;
import com.lajospolya.spotifyapiwrapper.reflection.ReflectiveSpotifyClientService;
import com.lajospolya.spotifyapiwrapper.response.AuthorizingToken;
import com.lajospolya.spotifyapiwrapper.spotifyexception.SpotifyRequestAuthorizationException;
import com.lajospolya.spotifyapiwrapper.spotifyexception.SpotifyRequestBuilderException;
import com.lajospolya.spotifyapiwrapper.spotifyexception.SpotifyResponseException;
import com.lajospolya.spotifyapiwrapper.spotifyrequest.AbstractSpotifyRequest;
import com.lajospolya.spotifyapiwrapper.spotifyrequest.AuthorizationCodeFlow;
import com.lajospolya.spotifyapiwrapper.spotifyrequest.ClientCredentialsFlow;
import com.lajospolya.spotifyapiwrapper.spotifyrequest.PostRefreshToken;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.net.http.HttpRequest;

public class SpotifyApiClient
{
    private AuthorizingToken apiTokenResponse;
    private String basicToken;
    private String bearerToken;

    private Long timeOfAuthorization;
    private IReflectiveSpotifyClientService reflectiveSpotifyClientService;
    private ISpotifyApiClientService spotifyApiClientService;

    private static final String BASIC_AUTHORIZATION = "Basic ";

    public static SpotifyApiClient createClientCredentialsFlowClient(String clientId, String clientSecret) throws SpotifyRequestAuthorizationException
    {
        return new SpotifyApiClient(clientId, clientSecret);
    }

    public static SpotifyApiClient createAuthorizationFlowClient(String clientId, String clientSecret, String code, String redirectUri) throws SpotifyRequestAuthorizationException
    {
        return new SpotifyApiClient(clientId, clientSecret, code, redirectUri);
    }

    // Constructor for testing
    SpotifyApiClient(IReflectiveSpotifyClientService reflectiveSpotifyClientService,
                     ISpotifyApiClientService spotifyApiClientService, AuthorizingToken apiTokenResponse)
    {
        this.reflectiveSpotifyClientService = reflectiveSpotifyClientService;
        this.spotifyApiClientService = spotifyApiClientService;
        this.apiTokenResponse = apiTokenResponse;
    }

    private SpotifyApiClient()
    {
        this.reflectiveSpotifyClientService = new ReflectiveSpotifyClientService();
        this.spotifyApiClientService = new SpotifyApiClientService();
        this.timeOfAuthorization = System.currentTimeMillis();
    }

    private SpotifyApiClient(String clientId, String clientSecret, String code, String redirectUri) throws SpotifyRequestAuthorizationException
    {
        this();

        AuthorizationCodeFlow authorizationRequest = new AuthorizationCodeFlow.Builder(code, redirectUri).build();
        authorizeClient(authorizationRequest, clientId, clientSecret);
    }

    private SpotifyApiClient(String clientId, String clientSecret) throws SpotifyRequestAuthorizationException
    {
        this();

        ClientCredentialsFlow authorizationRequest = new ClientCredentialsFlow.Builder().build();
        authorizeClient(authorizationRequest, clientId, clientSecret);
    }

    private void authorizeClient(AbstractSpotifyRequest<AuthorizingToken> authorizationRequest, String clientId, String clientSecret)
    {
        String base64EncodedAuthKey = spotifyApiClientService.getBase64EncodedAuthorizationKey(clientId, clientSecret);

        basicToken = BASIC_AUTHORIZATION + base64EncodedAuthKey;
        apiTokenResponse = sendRequest(authorizationRequest, basicToken);
        bearerToken = apiTokenResponse.getTokenType() + " " + apiTokenResponse.getAccessToken();
    }

    public <T> T sendRequest(AbstractSpotifyRequest<T> spotifyRequest)
            throws SpotifyRequestAuthorizationException, SpotifyRequestBuilderException, SpotifyResponseException
    {
        validateTokenHasNotExpired();

        return sendRequest(spotifyRequest, this.bearerToken);
    }

    private void validateTokenHasNotExpired() throws SpotifyRequestAuthorizationException
    {
        if(spotifyApiClientService.hasTokenExpired(this.timeOfAuthorization, this.apiTokenResponse.getExpiresIn()))
        {
            throw new SpotifyRequestAuthorizationException("Access Token Has Expired");
        }
    }

    private <T> T sendRequest(AbstractSpotifyRequest<T> spotifyRequest, String accessToken)
            throws SpotifyRequestBuilderException, SpotifyResponseException
    {
        try
        {
            this.reflectiveSpotifyClientService.setAccessTokenOfRequest(spotifyRequest, accessToken);

            HttpRequest request = this.reflectiveSpotifyClientService.buildRequest(spotifyRequest);

            Type genericType = this.reflectiveSpotifyClientService.getParameterizedTypeOfRequest(spotifyRequest);

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

    public void reauthorize() {
        if(canRefresh())
        {
            PostRefreshToken postRefreshToken = new PostRefreshToken.Builder(apiTokenResponse.getRefresh_token())
                    .build();
            apiTokenResponse = sendRequest(postRefreshToken, basicToken);
        }
        else
        {
            ClientCredentialsFlow clientCredentialsFlow = new ClientCredentialsFlow.Builder().build();
            apiTokenResponse = sendRequest(clientCredentialsFlow, basicToken);
        }
        timeOfAuthorization = System.currentTimeMillis();
    }

    private boolean canRefresh()
    {
        return apiTokenResponse.getRefresh_token() != null;
    }
}
