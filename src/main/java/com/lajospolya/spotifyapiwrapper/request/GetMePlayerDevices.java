package com.lajospolya.spotifyapiwrapper.request;

import com.lajospolya.spotifyapiwrapper.response.Devices;

import java.net.http.HttpRequest;

/**
 * @author Lajos Polya
 *
 * Represents the endpoint at GET https://api.spotify.com/v1/me/player/devices as descrbibed at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
public class GetMePlayerDevices extends AbstractSpotifyRequest<Devices>
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
            return new GetMePlayerDevices(spotifyRequestBuilder.GET());
        }
    }
}
