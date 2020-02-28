package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import com.lajospolya.spotifyapiwrapper.response.UserPrivate;
import org.springframework.web.util.UriComponentsBuilder;

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
            UriComponentsBuilder requestUriBuilder =  UriComponentsBuilder.fromUriString(REQUEST_URI_STRING);

            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(requestUriBuilder.build().toUri())
                    .GET();
            return new GetMe(requestBuilder);
        }
    }
}
