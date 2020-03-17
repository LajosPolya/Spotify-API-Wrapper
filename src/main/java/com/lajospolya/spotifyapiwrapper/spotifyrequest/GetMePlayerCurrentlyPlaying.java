package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import java.net.http.HttpRequest;

public final class GetMePlayerCurrentlyPlaying extends AbstractSpotifyRequest<String>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "me/player/currently-playing";

    private GetMePlayerCurrentlyPlaying(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder<GetMePlayerCurrentlyPlaying>
    {
        private String market;

        public Builder(String market) throws IllegalArgumentException
        {
            spotifyRequestParamValidationService.validateParametersNotNull(market);
            this.market = market;
        }

        @Override
        public GetMePlayerCurrentlyPlaying build()
        {
            SpotifyRequestBuilder spotifyRequestBuilder = new SpotifyRequestBuilder(REQUEST_URI_STRING);
            spotifyRequestBuilder.queryParam(MARKET_QUERY_PARAM, market);

            return new GetMePlayerCurrentlyPlaying(spotifyRequestBuilder.createGetRequests());
        }
    }
}
