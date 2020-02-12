package com.lajospolya.spotifyapiwrapper.authorization;

import com.lajospolya.spotifyapiwrapper.client.SpotifyApiClient;
import com.lajospolya.spotifyapiwrapper.spotifyexception.SpotifyRequestAuthorizationException;

import java.io.IOException;

public class SpotifyAuthorizationManager
{
    public static SpotifyApiClient getAuthorizedApiClient(String clientId, String secretKey)
    {
        SpotifyAuthorize authorizer = new SpotifyAuthorize.Builder(clientId, secretKey)
                .build();

        try
        {
            return new SpotifyApiClient(authorizer.sendRequest());
        }
        catch (InterruptedException | IOException e)
        {
            throw new SpotifyRequestAuthorizationException("There was an error authorizing the client");
        }
    }
}
