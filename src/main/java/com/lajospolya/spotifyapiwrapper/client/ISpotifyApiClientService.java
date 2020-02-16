package com.lajospolya.spotifyapiwrapper.client;

import com.lajospolya.spotifyapiwrapper.spotifyexception.SpotifyResponseException;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.http.HttpRequest;

public interface ISpotifyApiClientService
{
    <T> T sendRequestAndFetchResponse(HttpRequest request, Type typeOfReturnValue) throws IOException, InterruptedException, SpotifyResponseException;

    String getBase64EncodedAuthorizationKey(String clientId, String clientSecret);
}
