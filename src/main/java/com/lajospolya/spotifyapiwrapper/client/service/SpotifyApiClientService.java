package com.lajospolya.spotifyapiwrapper.client.service;

import com.lajospolya.spotifyapiwrapper.component.*;
import com.lajospolya.spotifyapiwrapper.spotifyexception.SpotifyResponseException;

import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class SpotifyApiClientService implements ISpotifyApiClientService
{
    private ISpotifyClient client;

    public SpotifyApiClientService()
    {
        client = SpotifyClientComponentsFactory.spotifyClient();
    }

    @Override
    public <T> T sendRequestAndFetchResponse(ISpotifyRequest<T> request, Type typeOfReturnValue) throws SpotifyResponseException
    {
        ISpotifyResponse<T> response = client.send(request, typeOfReturnValue);
        return response.body();
    }

    @Override
    public <T> ISpotifyAsyncResponse<T> sendRequestAndFetchResponseAsync(ISpotifyRequest<T> request, Type typeOfReturnValue) throws SpotifyResponseException
    {
        ISpotifyAsyncResponse<T> asyncResponse = client.sendAsync(request, typeOfReturnValue);
        return  asyncResponse;
    }

    @Override
    public String getBase64EncodedAuthorizationKey(String clientId, String clientSecret)
    {
        byte[] authorizationKey = (clientId + ":" + clientSecret).getBytes(StandardCharsets.UTF_8);
        return Base64.getEncoder().encodeToString(authorizationKey);
    }

    @Override
    public Boolean hasTokenExpired(Long timeOfAuthorization, Integer expiredIn)
    {
        return (System.currentTimeMillis() - timeOfAuthorization) / 1000L > expiredIn;
    }
}
