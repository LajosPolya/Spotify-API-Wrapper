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

    private SpotifyRequestBuilder requestBuilder;
    private String accessToken;

    AbstractSpotifyRequest(SpotifyRequestBuilder requestBuilder)
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
        requestBuilder.authorization(this.accessToken);
    }
}
