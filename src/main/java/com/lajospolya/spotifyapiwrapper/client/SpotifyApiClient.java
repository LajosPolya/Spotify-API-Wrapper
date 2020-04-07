package com.lajospolya.spotifyapiwrapper.client;

import com.lajospolya.spotifyapiwrapper.client.service.ISpotifyApiClientService;
import com.lajospolya.spotifyapiwrapper.client.service.SpotifyApiClientService;
import com.lajospolya.spotifyapiwrapper.reflection.IReflectiveSpotifyClientService;
import com.lajospolya.spotifyapiwrapper.reflection.ReflectiveSpotifyClientService;
import com.lajospolya.spotifyapiwrapper.internal.ISpotifyRequest;
import com.lajospolya.spotifyapiwrapper.response.AuthorizingToken;
import com.lajospolya.spotifyapiwrapper.spotifyexception.SpotifyRequestAuthorizationException;
import com.lajospolya.spotifyapiwrapper.spotifyexception.SpotifyRequestBuilderException;
import com.lajospolya.spotifyapiwrapper.spotifyexception.SpotifyResponseException;
import com.lajospolya.spotifyapiwrapper.request.AbstractSpotifyRequest;
import com.lajospolya.spotifyapiwrapper.request.PostAuthorizationCodeFlow;
import com.lajospolya.spotifyapiwrapper.request.PostClientCredentialsFlow;
import com.lajospolya.spotifyapiwrapper.request.PostRefreshToken;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.concurrent.CompletableFuture;

/**
 * @author Lajos Polya
 *
 * This class represents a Spotify client.
 * The client is instantiated by using one of the two static factory methods which returns an authorized client.
 * Once the user has an authorized client it can send Spotify requests with the client.
 */
public class SpotifyApiClient
{
    private AuthorizingToken apiTokenResponse;
    private String basicToken;
    private String bearerToken;

    private Long timeOfAuthorization;
    private IReflectiveSpotifyClientService reflectiveSpotifyClientService;
    private ISpotifyApiClientService spotifyApiClientService;

    private static final String BASIC_AUTHORIZATION = "Basic ";

    /**
     *
     * @param clientId the client id of a Spotify app retrieved from the Spotify Developer Dashboard
     * @param clientSecret the secret of a Spotify app retrieved from the Spotify Developer Dashboard
     * @return an authorized client using the client credentials flow
     * @throws SpotifyRequestAuthorizationException when the client cannot be authorized
     */
    public static SpotifyApiClient createClientCredentialsFlowClient(String clientId, String clientSecret) throws SpotifyRequestAuthorizationException
    {
        return new SpotifyApiClient(clientId, clientSecret);
    }

    /**
     *
     * @param clientId the client id of a Spotify app retrieved from the Spotify Developer Dashboard
     * @param clientSecret the secret of a Spotify app retrieved from the Spotify Developer Dashboard
     * @param code the code returned by the client when a user authorized this app
     * @param redirectUri the redirect url the app was authorized against
     * @return an authorized client using the authorization flow
     * @throws SpotifyRequestAuthorizationException when the client cannot be authorized
     */
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

        PostAuthorizationCodeFlow authorizationRequest = new PostAuthorizationCodeFlow.Builder(code, redirectUri).build();
        authorizeClient(authorizationRequest, clientId, clientSecret);
    }

    private SpotifyApiClient(String clientId, String clientSecret) throws SpotifyRequestAuthorizationException
    {
        this();

        PostClientCredentialsFlow authorizationRequest = new PostClientCredentialsFlow.Builder().build();
        authorizeClient(authorizationRequest, clientId, clientSecret);
    }

    private void authorizeClient(AbstractSpotifyRequest<AuthorizingToken> authorizationRequest, String clientId, String clientSecret)
    {
        String base64EncodedAuthKey = spotifyApiClientService.getBase64EncodedAuthorizationKey(clientId, clientSecret);

        basicToken = BASIC_AUTHORIZATION + base64EncodedAuthKey;
        apiTokenResponse = sendRequest(authorizationRequest, basicToken);
        bearerToken = apiTokenResponse.getTokenType() + " " + apiTokenResponse.getAccessToken();
    }

    /**
     * Sends a request synchronously
     * @param spotifyRequest the request to be sent
     * @param <T> type of the response body of the request
     * @return serialize response body of the response
     * @throws SpotifyRequestAuthorizationException when the client isn't authorized to send request
     * @throws SpotifyRequestBuilderException when the request wasn't built successfully, this occurs if it is missing
     * the mandatory private fields and methods needed to build the request
     * @throws SpotifyResponseException when the response header contains an erroneous status code
     */
    public <T> T sendRequest(AbstractSpotifyRequest<T> spotifyRequest)
            throws SpotifyRequestAuthorizationException, SpotifyRequestBuilderException, SpotifyResponseException
    {
        validateTokenHasNotExpired();

        return sendRequest(spotifyRequest, bearerToken);
    }

    /**
     * Sends a request asynchronously
     * @param spotifyRequest the request to be sent
     * @param <T> type of the response body of the request
     * @return serialize response body of the response
     * @throws SpotifyRequestAuthorizationException when the client isn't authorized to send request
     * @throws SpotifyRequestBuilderException when the request wasn't built successfully, this occurs if it is missing
     * the mandatory private fields and methods needed to build the request
     * @throws SpotifyResponseException when the response header contains an erroneous status code
     */
    public <T> CompletableFuture<T> sendRequestAsync(AbstractSpotifyRequest<T> spotifyRequest)
            throws SpotifyRequestAuthorizationException, SpotifyRequestBuilderException, SpotifyResponseException
    {
        validateTokenHasNotExpired();

        return sendRequestAsync(spotifyRequest, bearerToken);
    }

    private void validateTokenHasNotExpired() throws SpotifyRequestAuthorizationException
    {
        if(spotifyApiClientService.hasTokenExpired(timeOfAuthorization, apiTokenResponse.getExpiresIn()))
        {
            throw new SpotifyRequestAuthorizationException("Access Token Has Expired");
        }
    }

    private <T> T sendRequest(AbstractSpotifyRequest<T> spotifyRequest, String accessToken)
            throws SpotifyRequestBuilderException, SpotifyResponseException
    {
        try
        {
            ISpotifyRequest request = authorizeAndBuildRequest(spotifyRequest, accessToken);
            Type genericType = reflectiveSpotifyClientService.getParameterizedTypeOfRequest(spotifyRequest);

            return spotifyApiClientService.sendRequestAndFetchResponse(request, genericType);
        }
        catch (InterruptedException | IOException e)
        {
            throw new SpotifyResponseException("An exception occurred while sending the request");
        }
    }

    private <T> CompletableFuture<T> sendRequestAsync(AbstractSpotifyRequest<T> spotifyRequest, String accessToken)
            throws SpotifyRequestBuilderException, SpotifyResponseException
    {
        ISpotifyRequest request = authorizeAndBuildRequest(spotifyRequest, accessToken);
        Type genericType = reflectiveSpotifyClientService.getParameterizedTypeOfRequest(spotifyRequest);

        return spotifyApiClientService.sendRequestAndFetchResponseAsync(request, genericType);
    }

    private <T> ISpotifyRequest authorizeAndBuildRequest(AbstractSpotifyRequest<T> spotifyRequest, String accessToken)
    {
        try
        {
            reflectiveSpotifyClientService.setAccessTokenOfRequest(spotifyRequest, accessToken);

            return reflectiveSpotifyClientService.buildRequest(spotifyRequest);
        }
        catch (InvocationTargetException | IllegalAccessException e)
        {
            throw new SpotifyRequestBuilderException("Unable to build the request");
        }
    }

    /**
     * Reauthorizes the client synchronously, if the client has a refresh token it will refresh it, otherwise it'll
     * reauthorize using the client credentials flow
     */
    public void reauthorize()
    {
        if(canRefresh())
        {
            PostRefreshToken postRefreshToken = new PostRefreshToken.Builder(apiTokenResponse.getRefresh_token())
                    .build();
            apiTokenResponse = sendRequest(postRefreshToken, basicToken);
        }
        else
        {
            PostClientCredentialsFlow postClientCredentialsFlow = new PostClientCredentialsFlow.Builder().build();
            apiTokenResponse = sendRequest(postClientCredentialsFlow, basicToken);
        }
        timeOfAuthorization = System.currentTimeMillis();
    }

    /**
     * Reauthorizes the client asynchronously, if the client has a refresh token it will refresh it, otherwise it'll
     * reauthorize using the client credentials flow
     * @return a void completable future
     */
    public CompletableFuture<Void> reauthorizeAsync()
    {
        if(canRefresh())
        {
            PostRefreshToken postRefreshToken = new PostRefreshToken.Builder(apiTokenResponse.getRefresh_token())
                    .build();
            return sendRequestAsync(postRefreshToken, basicToken).thenAccept(response ->
            {
                apiTokenResponse = response;
                timeOfAuthorization = System.currentTimeMillis();
            });
        }
        else
        {
            PostClientCredentialsFlow postClientCredentialsFlow = new PostClientCredentialsFlow.Builder().build();
            return sendRequestAsync(postClientCredentialsFlow, basicToken).thenAccept(response ->
            {
                apiTokenResponse = response;
                timeOfAuthorization = System.currentTimeMillis();
            });
        }
    }

    private boolean canRefresh()
    {
        return apiTokenResponse.getRefresh_token() != null;
    }
}
