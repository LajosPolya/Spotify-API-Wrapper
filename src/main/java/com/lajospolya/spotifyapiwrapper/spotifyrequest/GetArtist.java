package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import com.lajospolya.spotifyapiwrapper.client.response.Artist;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.http.HttpRequest;

public class GetArtist extends SpotifyRequest<Artist>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "artists/{id}";
    private static final UriComponentsBuilder REQUEST_URI =  UriComponentsBuilder.fromUriString(REQUEST_URI_STRING);

    private HttpRequest request;
    private HttpRequest.Builder requestBuilder;
    private String accessToken;

    private GetArtist(HttpRequest.Builder requestBuilder)
    {
        this.requestBuilder = requestBuilder;
    }

    private HttpRequest buildRequest()
    {
        return requestBuilder
                .header(AUTHORIZATION_HEADER, this.accessToken)
                .build();
    }

    public static class Builder
    {
        private String id;

        public Builder(String id)
        {
            this.id = id;
        }

        public GetArtist build()
        {
            // Required param validation
            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(REQUEST_URI.buildAndExpand(this.id).toUri())
                    .GET();
            return new GetArtist(requestBuilder);
        }
    }
}
