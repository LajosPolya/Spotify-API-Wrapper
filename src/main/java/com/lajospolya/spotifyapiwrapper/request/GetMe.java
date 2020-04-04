package com.lajospolya.spotifyapiwrapper.request;

import com.lajospolya.spotifyapiwrapper.response.UserPrivate;

import java.net.http.HttpRequest;

/**
 * @author Lajos Polya
 *
 * Represents the endpoint at GET https://api.spotify.com/v1/me as descrbibed at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
public class GetMe extends AbstractSpotifyRequest<UserPrivate>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "me";

    private GetMe(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder<GetMe>
    {

        public Builder() { }

        @Override
        public GetMe build()
        {
            SpotifyRequestBuilder spotifyRequestBuilder = new SpotifyRequestBuilder(REQUEST_URI_STRING);

            return new GetMe(spotifyRequestBuilder.GET());
        }
    }
}
