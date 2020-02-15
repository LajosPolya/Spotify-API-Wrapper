package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import com.lajospolya.spotifyapiwrapper.response.Albums;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.http.HttpRequest;
import java.util.List;

public final class GetAlbums extends AbstractSpotifyRequest<Albums>
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
                .build();
    }

    private void setAccessToken(String accessToken)
    {
        this.accessToken = accessToken;
        requestBuilder.setHeader(AUTHORIZATION_HEADER, this.accessToken);
    }

    public static class Builder extends AbstractBuilder
    {
        private List<String> albumIds;
        private String market;

        public Builder(List<String> albumIds) throws IllegalArgumentException
        {
            validateParametersNotNull(albumIds);
            this.albumIds = albumIds;
        }

        public GetAlbums build()
        {
            // Requires param validation
            UriComponentsBuilder requestUriBuilder =  UriComponentsBuilder.fromUriString(REQUEST_URI_STRING);
            String commaSeparatedIds = String.join(",", this.albumIds);
            requestUriBuilder.queryParam(IDS_QUERY_PARAM, commaSeparatedIds);

            addOptionalQueryParams(requestUriBuilder);

            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(requestUriBuilder.build().toUri())
                    .GET();
            return new GetAlbums(requestBuilder);
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
