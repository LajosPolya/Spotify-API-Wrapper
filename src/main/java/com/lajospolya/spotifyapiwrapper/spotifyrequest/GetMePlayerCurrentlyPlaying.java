package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import org.springframework.web.util.UriComponentsBuilder;

import java.net.http.HttpRequest;

public class GetMePlayerCurrentlyPlaying extends AbstractSpotifyRequest<String>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "me/player/currently-playing";

    private GetMePlayerCurrentlyPlaying(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder
    {
        private String market;

        public Builder(String market) throws IllegalArgumentException
        {
            validateParametersNotNull(market);
            this.market = market;
        }

        public GetMePlayerCurrentlyPlaying build()
        {
            UriComponentsBuilder requestUriBuilder =  UriComponentsBuilder.fromUriString(REQUEST_URI_STRING);

            requestUriBuilder.queryParam(MARKET_QUERY_PARAM, this.market);

            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(requestUriBuilder.build().toUri())
                    .GET();
            return new GetMePlayerCurrentlyPlaying(requestBuilder);
        }
    }
}
