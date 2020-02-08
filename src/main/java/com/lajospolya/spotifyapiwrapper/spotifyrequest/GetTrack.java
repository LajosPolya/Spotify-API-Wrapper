package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import com.lajospolya.spotifyapiwrapper.client.response.Track;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.http.HttpRequest;

public class GetTrack extends SpotifyRequest<Track>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "tracks/{id}";

    private HttpRequest request;
    private HttpRequest.Builder requestBuilder;
    private String accessToken;

    private GetTrack(HttpRequest.Builder requestBuilder)
    {
        this.requestBuilder = requestBuilder;
    }

    private HttpRequest buildRequest()
    {
        return requestBuilder
                .header(AUTHORIZATION_HEADER, this.accessToken)
                .build();
    }

    public static class Builder extends AbstractBuilder
    {
        private String trackId;

        public Builder(String trackId)
        {
            validateParametersNotNull(trackId);
            this.trackId = trackId;
        }

        public GetTrack build()
        {
            // Requires param validation
            UriComponentsBuilder requestUriBuilder =  UriComponentsBuilder.fromUriString(REQUEST_URI_STRING);
            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(requestUriBuilder.buildAndExpand(this.trackId).toUri())
                    .GET();
            return new GetTrack(requestBuilder);
        }
    }
}