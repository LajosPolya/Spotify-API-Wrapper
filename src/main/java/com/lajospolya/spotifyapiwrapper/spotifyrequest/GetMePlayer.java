package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import java.net.http.HttpRequest;

public class GetMePlayer extends AbstractSpotifyRequest<String>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "me/player";

    private GetMePlayer(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder
    {
        private String market;

        public Builder(String market)
        {
            validateParametersNotNull(market);
            this.market = market;
        }

        public GetMePlayer build()
        {
            SpotifyRequestBuilder spotifyRequestBuilder = new SpotifyRequestBuilder(REQUEST_URI_STRING);
            spotifyRequestBuilder.addQueryParam(MARKET_QUERY_PARAM, market);

            return new GetMePlayer(spotifyRequestBuilder.createGetRequests());
        }
    }
}