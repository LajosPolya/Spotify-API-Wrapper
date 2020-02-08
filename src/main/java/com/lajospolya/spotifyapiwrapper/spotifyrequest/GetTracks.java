package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import com.lajospolya.spotifyapiwrapper.client.response.Tracks;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.http.HttpRequest;
import java.util.List;

public class GetTracks extends SpotifyRequest<Tracks>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "tracks";

    private HttpRequest request;
    private HttpRequest.Builder requestBuilder;
    private String accessToken;

    private GetTracks(HttpRequest.Builder requestBuilder)
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
        private List<String> trackIds;

        public Builder(List<String> trackIds)
        {
            this.trackIds = trackIds;
        }

        public GetTracks build()
        {
            // Requires param validation
            UriComponentsBuilder requestUriBuilder =  UriComponentsBuilder.fromUriString(REQUEST_URI_STRING);
            String commaSeparatedIds = String.join(",", this.trackIds);
            requestUriBuilder.queryParam(IDS_QUERY_PARAM, commaSeparatedIds);
            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(requestUriBuilder.build().toUri())
                    .GET();
            return new GetTracks(requestBuilder);
        }
    }
}
