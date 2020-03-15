package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import com.lajospolya.spotifyapiwrapper.response.UserPrivate;

import java.net.http.HttpRequest;

public class GetMe extends AbstractSpotifyRequest<UserPrivate>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "me";

    private GetMe(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder
    {

        public Builder() { }

        public GetMe build()
        {
            SpotifyRequestBuilder spotifyRequestBuilder = new SpotifyRequestBuilder(REQUEST_URI_STRING);

            return new GetMe(spotifyRequestBuilder.createGetRequests());
        }
    }
}
