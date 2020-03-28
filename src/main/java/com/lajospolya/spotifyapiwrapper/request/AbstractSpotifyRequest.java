package com.lajospolya.spotifyapiwrapper.request;

import com.lajospolya.spotifyapiwrapper.spotifyexception.SpotifyRequestBuilderException;

import java.net.http.HttpRequest;

/**
 * This is the base class for all Spotify Requests.
 * The SpotifyApiClient uses private methods on this field to authorize and built the request
 * @author Lajos Polya
 * @param <T> the type of the Response Body
 */
public abstract class AbstractSpotifyRequest<T>
{
    static final String SPOTIFY_V1_API_URI = "https://api.spotify.com/v1/";
    static final String AUTHORIZATION_HEADER = "Authorization";

    private HttpRequest.Builder requestBuilder;
    private String accessToken;

    AbstractSpotifyRequest(HttpRequest.Builder requestBuilder)
    {
        this.requestBuilder = requestBuilder;
    }

    private HttpRequest reflectiveBuildRequest()
    {
        if(accessToken == null)
        {
            throw new SpotifyRequestBuilderException("Cannot Build a request with a null access token");
        }
        return this.requestBuilder
                .build();
    }

    private void reflectiveSetAccessToken(String accessToken)
    {
        this.accessToken = accessToken;
        requestBuilder.setHeader(AUTHORIZATION_HEADER, this.accessToken);
    }
}
