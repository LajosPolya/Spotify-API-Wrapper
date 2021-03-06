package com.lajospolya.spotifyapiwrapper.request;

import com.lajospolya.spotifyapiwrapper.component.ISpotifyRequestBuilder;
import com.lajospolya.spotifyapiwrapper.component.SpotifyClientComponentsFactory;
import com.lajospolya.spotifyapiwrapper.response.Episodes;

import java.util.List;

/**
 * @author Lajos Polya
 *
 * Represents the endpoint at GET https://api.spotify.com/v1/episodes as descrbibed at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
public class GetEpisodes extends AbstractSpotifyRequest<Episodes>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "episodes";

    private GetEpisodes(ISpotifyRequestBuilder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder<GetEpisodes>
    {
        private final List<String> episodeIds;

        private String market;

        public Builder(List<String> episodeIds) throws IllegalArgumentException
        {
            spotifyRequestParamValidationService.validateParametersNotNull(episodeIds);
            spotifyRequestParamValidationService.validateIds50(episodeIds);
            this.episodeIds = episodeIds;
        }

        @Override
        public GetEpisodes build()
        {
            ISpotifyRequestBuilder spotifyRequestBuilder = SpotifyClientComponentsFactory.spotifyRequestBuilder(REQUEST_URI_STRING)
            .queryParam(IDS_QUERY_PARAM, episodeIds);
            addOptionalQueryParams(spotifyRequestBuilder);

            return new GetEpisodes(spotifyRequestBuilder.GET());
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
