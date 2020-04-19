package com.lajospolya.spotifyapiwrapper.request;

import com.lajospolya.spotifyapiwrapper.internal.ISpotifyRequestBuilder;
import com.lajospolya.spotifyapiwrapper.internal.SpotifyClientComponentsFactory;
import com.lajospolya.spotifyapiwrapper.response.CurrentlyPlayingContext;

/**
 * @author Lajos Polya
 *
 * Represents the endpoint at GET https://api.spotify.com/v1/me/player as descrbibed at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
public class GetMePlayer extends AbstractSpotifyRequest<CurrentlyPlayingContext>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "me/player";

    private GetMePlayer(ISpotifyRequestBuilder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder<GetMePlayer>
    {
        private String market;

        public Builder(String market) throws IllegalArgumentException
        {
            spotifyRequestParamValidationService.validateParametersNotNull(market);
            this.market = market;
        }

        @Override
        public GetMePlayer build()
        {
            ISpotifyRequestBuilder spotifyRequestBuilder = SpotifyClientComponentsFactory.spotifyRequestBuilder(REQUEST_URI_STRING);
            spotifyRequestBuilder.queryParam(MARKET_QUERY_PARAM, market);

            return new GetMePlayer(spotifyRequestBuilder.GET());
        }
    }
}
