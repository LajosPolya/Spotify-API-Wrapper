package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import com.lajospolya.spotifyapiwrapper.response.TracksAudioFeatures;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.http.HttpRequest;
import java.util.List;

public final class GetSeveralAudioFeatures extends AbstractSpotifyRequest<TracksAudioFeatures>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "audio-features";

    private HttpRequest request;
    private HttpRequest.Builder requestBuilder;
    private String accessToken;

    private GetSeveralAudioFeatures(HttpRequest.Builder requestBuilder)
    {
        this.requestBuilder = requestBuilder;
    }

    private HttpRequest buildRequest()
    {
        return requestBuilder
                .build();
    }

    private void setAccessToken(String accessToken)
    {
        this.accessToken = accessToken;
        requestBuilder.setHeader(AUTHORIZATION_HEADER, this.accessToken);
    }

    public static class Builder extends AbstractBuilder
    {
        private List<String> trackIds;

        public Builder(List<String> trackIds)
        {
            validateParametersNotNull(trackIds);
            this.trackIds = trackIds;
        }

        public GetSeveralAudioFeatures build()
        {
            // Requires param validation
            UriComponentsBuilder requestUriBuilder =  UriComponentsBuilder.fromUriString(REQUEST_URI_STRING);
            String commaSeparatedIds = String.join(",", this.trackIds);
            requestUriBuilder.queryParam(IDS_QUERY_PARAM, commaSeparatedIds);
            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(requestUriBuilder.build().toUri())
                    .GET();
            return new GetSeveralAudioFeatures(requestBuilder);
        }
    }
}
