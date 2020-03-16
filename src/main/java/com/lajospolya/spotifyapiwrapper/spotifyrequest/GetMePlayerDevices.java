package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import com.lajospolya.spotifyapiwrapper.response.Devices;

import java.net.http.HttpRequest;

public final class GetMePlayerDevices extends AbstractSpotifyRequest<Devices>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "me/player/devices";

    private GetMePlayerDevices(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder<GetMePlayerDevices>
    {

        public Builder() { }

        @Override
        public GetMePlayerDevices build()
        {
            SpotifyRequestBuilder spotifyRequestBuilder = new SpotifyRequestBuilder(REQUEST_URI_STRING);
            return new GetMePlayerDevices(spotifyRequestBuilder.createGetRequests());
        }
    }
}
