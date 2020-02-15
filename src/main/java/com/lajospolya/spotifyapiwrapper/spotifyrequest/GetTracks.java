package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import com.lajospolya.spotifyapiwrapper.response.Tracks;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.http.HttpRequest;
import java.util.List;

public final class GetTracks extends AbstractSpotifyRequest<Tracks>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "tracks";

    private GetTracks(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder
    {
        private List<String> trackIds;
        private String market;

        public Builder(List<String> trackIds)
        {
            validateParametersNotNull(trackIds);
            this.trackIds = trackIds;
        }

        public GetTracks build()
        {
            // Requires param validation
            UriComponentsBuilder requestUriBuilder =  UriComponentsBuilder.fromUriString(REQUEST_URI_STRING);
            String commaSeparatedIds = String.join(",", this.trackIds);
            requestUriBuilder.queryParam(IDS_QUERY_PARAM, commaSeparatedIds);

            addOptionalQueryParams(requestUriBuilder);

            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(requestUriBuilder.build().toUri())
                    .GET();
            return new GetTracks(requestBuilder);
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
