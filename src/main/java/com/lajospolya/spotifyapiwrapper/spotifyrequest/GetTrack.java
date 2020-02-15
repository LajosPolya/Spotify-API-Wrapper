package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import com.lajospolya.spotifyapiwrapper.response.Track;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.http.HttpRequest;

public final class GetTrack extends AbstractSpotifyRequest<Track>
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
                .build();
    }

    private void setAccessToken(String accessToken)
    {
        this.accessToken = accessToken;
        requestBuilder.setHeader(AUTHORIZATION_HEADER, this.accessToken);
    }

    public static class Builder extends AbstractBuilder
    {
        private String trackId;
        private String market;

        public Builder(String trackId)
        {
            validateParametersNotNull(trackId);
            this.trackId = trackId;
        }

        public GetTrack build()
        {
            // Requires param validation
            UriComponentsBuilder requestUriBuilder =  UriComponentsBuilder.fromUriString(REQUEST_URI_STRING);

            addOptionalQueryParams(requestUriBuilder);

            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(requestUriBuilder.buildAndExpand(this.trackId).toUri())
                    .GET();
            return new GetTrack(requestBuilder);
        }

        private void addOptionalQueryParams(UriComponentsBuilder requestUriBuilder)
        {
            if(this.market != null)
            {
                requestUriBuilder.queryParam(MARKET_QUERY_PARAM, this.market);
            }
        }

        public Builder market(String market)
        {
            this.market = market;
            return this;
        }
    }
}
