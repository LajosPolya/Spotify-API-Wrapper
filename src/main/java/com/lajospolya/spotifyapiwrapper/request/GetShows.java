package com.lajospolya.spotifyapiwrapper.request;

import com.lajospolya.spotifyapiwrapper.component.ISpotifyRequestBuilder;
import com.lajospolya.spotifyapiwrapper.component.SpotifyClientComponentsFactory;
import com.lajospolya.spotifyapiwrapper.response.Shows;

import java.util.List;

/**
 * @author Lajos Polya
 *
 * Represents the endpoint at GET https://api.spotify.com/v1/shows as descrbibed at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
public class GetShows extends AbstractSpotifyRequest<Shows>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "shows";

    private GetShows(ISpotifyRequestBuilder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder<GetShows>
    {
        private final List<String> showIds;
        private String market;

        public Builder(List<String> showIds) throws IllegalArgumentException
        {
            spotifyRequestParamValidationService.validateParametersNotNull(showIds);
            spotifyRequestParamValidationService.validateIds50(showIds);
            this.showIds = showIds;
        }

        @Override
        public GetShows build()
        {
            ISpotifyRequestBuilder spotifyRequestBuilder = SpotifyClientComponentsFactory.spotifyRequestBuilder(REQUEST_URI_STRING);
            spotifyRequestBuilder.queryParam(IDS_QUERY_PARAM, showIds);
            addOptionalQueryParams(spotifyRequestBuilder);

            return new GetShows(spotifyRequestBuilder.GET());
        }

        private void addOptionalQueryParams(ISpotifyRequestBuilder requestUriBuilder)
        {
            if(market != null)
            {
                requestUriBuilder.queryParam(MARKET_QUERY_PARAM, market);
            }
        }

        public Builder market(String market)
        {
            this.market = market;
            return this;
        }
    }
}
