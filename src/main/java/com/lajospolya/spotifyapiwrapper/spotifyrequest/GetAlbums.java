package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import com.lajospolya.spotifyapiwrapper.client.response.Albums;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.http.HttpRequest;
import java.util.List;

public class GetAlbums extends SpotifyRequest<Albums>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "albums";

    private HttpRequest request;
    private HttpRequest.Builder requestBuilder;
    private String accessToken;

    private GetAlbums(HttpRequest.Builder requestBuilder)
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
        private List<String> albumIds;

        public Builder(List<String> albumIds)
        {
            this.albumIds = albumIds;
        }

        public GetAlbums build()
        {
            // Requires param validation
            UriComponentsBuilder requestUriBuilder =  UriComponentsBuilder.fromUriString(REQUEST_URI_STRING);
            String commaSeparatedIds = String.join(",", this.albumIds);
            requestUriBuilder.queryParam(IDS_QUERY_PARAM, commaSeparatedIds);
            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(requestUriBuilder.build().toUri())
                    .GET();
            return new GetAlbums(requestBuilder);
        }
    }
}
