package com.lajospolya.spotifyapiwrapper.request;

import com.lajospolya.spotifyapiwrapper.internal.ISpotifyRequestBuilder;
import com.lajospolya.spotifyapiwrapper.internal.SpotifyClientComponentsFactory;
import com.lajospolya.spotifyapiwrapper.response.Devices;

/**
 * @author Lajos Polya
 *
 * Represents the endpoint at GET https://api.spotify.com/v1/me/player/devices as descrbibed at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
public class GetMePlayerDevices extends AbstractSpotifyRequest<Devices>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "me/player/devices";

    private GetMePlayerDevices(ISpotifyRequestBuilder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder<GetMePlayerDevices>
    {

        public Builder() { }

        @Override
        public GetMePlayerDevices build()
        {
            ISpotifyRequestBuilder spotifyRequestBuilder = SpotifyClientComponentsFactory.spotifyRequestBuilder(REQUEST_URI_STRING);
            return new GetMePlayerDevices(spotifyRequestBuilder.GET());
        }
    }
}
