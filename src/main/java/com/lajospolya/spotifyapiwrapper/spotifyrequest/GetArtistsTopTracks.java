package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import com.lajospolya.spotifyapiwrapper.client.response.ArtistsTopTracks;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.http.HttpRequest;

public class GetArtistsTopTracks extends SpotifyRequest<ArtistsTopTracks>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "artists/{id}/top-tracks";

    private HttpRequest request;
    private HttpRequest.Builder requestBuilder;
    private String accessToken;

    private GetArtistsTopTracks(HttpRequest.Builder requestBuilder)
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
        private String artistId;
        private String market;

        public Builder(String artistId, String market)
        {
            this.artistId = artistId;
            this.market = market;
        }

        public GetArtistsTopTracks build()
        {
            // Requires param validation
            UriComponentsBuilder requestUriBuilder =  UriComponentsBuilder.fromUriString(REQUEST_URI_STRING);
            requestUriBuilder.queryParam(MARKET_QUERY_PARAM, market);
            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(requestUriBuilder.buildAndExpand(this.artistId).toUri())
                    .GET();
            return new GetArtistsTopTracks(requestBuilder);
        }
    }
}
