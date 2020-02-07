package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import com.lajospolya.spotifyapiwrapper.client.response.AudioFeatures;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.http.HttpRequest;

public class GetAudioFeatures extends SpotifyRequest<AudioFeatures>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "audio-features/{id}";
    private static final UriComponentsBuilder REQUEST_URI =  UriComponentsBuilder.fromUriString(REQUEST_URI_STRING);

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
                .header(AUTHORIZATION_HEADER, this.accessToken)
                .build();
    }

    public static class Builder
    {
        private String trackId;

        public Builder(String trackId)
        {
            this.trackId = trackId;
        }

        public GetAudioFeatures build()
        {
            // Requires param validation
            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(REQUEST_URI.buildAndExpand(this.trackId).toUri())
                    .GET();
            return new GetAudioFeatures(requestBuilder);
        }
    }
}
