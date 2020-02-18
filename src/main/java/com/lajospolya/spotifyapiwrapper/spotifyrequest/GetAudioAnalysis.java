package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import org.springframework.web.util.UriComponentsBuilder;

import java.net.http.HttpRequest;

public final class GetAudioAnalysis extends AbstractSpotifyRequest<String>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "audio-analysis/{id}";

    private GetAudioAnalysis(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder
    {
        private String trackId;

        public Builder(String trackId) throws IllegalArgumentException
        {
            validateParametersNotNull(trackId);
            this.trackId = trackId;
        }

        public GetAudioAnalysis build()
        {
            UriComponentsBuilder requestUriBuilder =  UriComponentsBuilder.fromUriString(REQUEST_URI_STRING);
            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(requestUriBuilder.buildAndExpand(this.trackId).toUri())
                    .GET();
            return new GetAudioAnalysis(requestBuilder);
        }
    }
}
