package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import com.lajospolya.spotifyapiwrapper.response.Devices;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.http.HttpRequest;

public class GetMeDevices extends AbstractSpotifyRequest<Devices>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "me/player/devices";

    private GetMeDevices(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder
    {

        public Builder() { }

        public GetMeDevices build()
        {
            UriComponentsBuilder requestUriBuilder =  UriComponentsBuilder.fromUriString(REQUEST_URI_STRING);

            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(requestUriBuilder.build().toUri())
                    .GET();
            return new GetMeDevices(requestBuilder);
        }
    }
}
