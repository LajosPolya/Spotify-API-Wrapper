package com.lajospolya.spotifyapiwrapper.client.service;

import com.lajospolya.spotifyapiwrapper.spotifyexception.SpotifyResponseException;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.http.HttpRequest;
import java.util.concurrent.CompletableFuture;

public interface ISpotifyApiClientService
{
    <T> T sendRequestAndFetchResponse(HttpRequest request, Type typeOfReturnValue) throws IOException, InterruptedException, SpotifyResponseException;

    <T> CompletableFuture<T> sendRequestAndFetchResponseAsync(HttpRequest request, Type typeOfReturnValue) throws SpotifyResponseException;

    String getBase64EncodedAuthorizationKey(String clientId, String clientSecret);

    Boolean hasTokenExpired(Long timeOfAuthorization, Integer expiredIn);
}
