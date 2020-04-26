package com.lajospolya.spotifyapiwrapper.client.service;

import com.lajospolya.spotifyapiwrapper.component.ISpotifyAsyncResponse;
import com.lajospolya.spotifyapiwrapper.component.ISpotifyRequest;
import com.lajospolya.spotifyapiwrapper.spotifyexception.SpotifyResponseException;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * @author Lajos Polya
 *
 * This interface has all helper methods for the SpotifyClient
 */
public interface ISpotifyApiClientService
{
    /**
     * Sends the request and returns a serialized version of the response
     * @param request the request to be sent
     * @param typeOfReturnValue the type of the response body of the request
     * @param <T> should be equivalent to typeOfReturnValue
     * @return returns the serialized response body of the request
     * @throws SpotifyResponseException when the response header has an erroneous status code
     * @throws IOException delegated exception
     * @throws InterruptedException delegated exception
     */
    <T> T sendRequestAndFetchResponse(ISpotifyRequest<?> request, Type typeOfReturnValue) throws IOException, InterruptedException, SpotifyResponseException;

    /**
     * Sends the request and returns a serialized version of the response
     * @param request the request to be sent
     * @param typeOfReturnValue the type of the response body of the request
     * @param <T> should be equivalent to typeOfReturnValue
     * @return returns the serialized response body of the request
     * @throws SpotifyResponseException when the response header has an erroneous status code
     */
    <T> ISpotifyAsyncResponse<T> sendRequestAndFetchResponseAsync(ISpotifyRequest<T> request, Type typeOfReturnValue) throws SpotifyResponseException;

    /**
     * Base64 encodes the clientId and clientSecret in the format clientId:clientSecret
     * @param clientId the client id of a Spotify app retrieved from the Spotify Developer Dashboard
     * @param clientSecret the secret of a Spotify app retrieved from the Spotify Developer Dashboard
     * @return Base64 encoded String with the format of clientId:clientSecret
     */
    String getBase64EncodedAuthorizationKey(String clientId, String clientSecret);

    /**
     * Checks if the authorization token has expired
     * @param timeOfAuthorization time of authorization in milliseconds
     * @param expiredIn numer of minutes the token is authorized for
     * @return true if time since authorization is greater than the time the token is set to expire, false otherwise
     */
    Boolean hasTokenExpired(Long timeOfAuthorization, Integer expiredIn);
}
