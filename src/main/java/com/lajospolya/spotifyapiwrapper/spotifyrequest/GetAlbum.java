package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import com.lajospolya.spotifyapiwrapper.client.response.Album;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.http.HttpRequest;

public class GetAlbum extends SpotifyRequest<Album>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "albums/{id}";

    private HttpRequest request;
    private HttpRequest.Builder requestBuilder;
    private String accessToken;

    private GetAlbum(HttpRequest.Builder requestBuilder)
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
        private String albumId;

        public Builder(String albumId)
        {
            this.albumId = albumId;
        }

        public GetAlbum build()
        {
            // Required param validation
            UriComponentsBuilder requestUriBuilder =  UriComponentsBuilder.fromUriString(REQUEST_URI_STRING);
            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(requestUriBuilder.buildAndExpand(this.albumId).toUri())
                    .GET();
            return new GetAlbum(requestBuilder);
        }
    }
}
