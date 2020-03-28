package com.lajospolya.spotifyapiwrapper.request;

import com.lajospolya.spotifyapiwrapper.response.CurrentlyPlaying;

import java.net.http.HttpRequest;

/**
 * @author Lajos Polya
 *
 * Represents the endpoint at GET https://api.spotify.com/v1/me/player/currently-playing as descrbibed at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
public class GetMePlayerCurrentlyPlaying extends AbstractSpotifyRequest<CurrentlyPlaying>
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