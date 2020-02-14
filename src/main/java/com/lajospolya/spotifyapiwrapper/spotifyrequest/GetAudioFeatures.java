package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import com.lajospolya.spotifyapiwrapper.response.AudioFeatures;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.http.HttpRequest;

public final class GetAudioFeatures extends AbstractSpotifyRequest<AudioFeatures>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "audio-features/{id}";

    private HttpRequest request;
    private HttpRequest.Builder requestBuilder;
    private String accessToken;

    private GetAudioFeatures(HttpRequest.Builder requestBuilder)
    {
        this.requestBuilder = requestBuilder;
    }

    private HttpRequest buildRequest()
    {
        return requestBuilder
                .setHeader(AUTHORIZATION_HEADER, this.accessToken)
                .build();
    }

    public static class Builder extends AbstractBuilder
    {
        private String trackId;

        public Builder(String trackId) throws IllegalArgumentException
        {
            validateParametersNotNull(trackId);
            this.trackId = trackId;
        }

        public GetAudioFeatures build()
        {
            // Requires param validation
            UriComponentsBuilder requestUriBuilder =  UriComponentsBuilder.fromUriString(REQUEST_URI_STRING);
            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(requestUriBuilder.buildAndExpand(this.trackId).toUri())
                    .GET();
            return new GetAudioFeatures(requestBuilder);
        }
    }
}
