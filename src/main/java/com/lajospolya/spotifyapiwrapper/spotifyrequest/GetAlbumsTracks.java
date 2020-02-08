package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import com.lajospolya.spotifyapiwrapper.client.response.Paging;
import com.lajospolya.spotifyapiwrapper.client.response.SimplifiedTrack;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.http.HttpRequest;

public class GetAlbumsTracks extends SpotifyRequest<Paging<SimplifiedTrack>>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "albums/{id}/tracks";

    private HttpRequest request;
    private HttpRequest.Builder requestBuilder;
    private String accessToken;

    private GetAlbumsTracks(HttpRequest.Builder requestBuilder)
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

        public GetAlbumsTracks build()
        {
            // Requires param validation
            UriComponentsBuilder requestUri =  UriComponentsBuilder.fromUriString(REQUEST_URI_STRING);
            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(requestUri.buildAndExpand(this.albumId).toUri())
                    .GET();
            return new GetAlbumsTracks(requestBuilder);
        }
    }
}
