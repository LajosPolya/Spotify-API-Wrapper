package com.lajospolya.spotifyapiwrapper.request;

import com.lajospolya.spotifyapiwrapper.response.Episodes;

import java.net.http.HttpRequest;
import java.util.List;

public class GetEpisodes extends AbstractSpotifyRequest<Episodes>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "episodes";

    private GetEpisodes(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder<GetEpisodes>
    {
        private List<String> episodeIds;

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
            SpotifyRequestBuilder spotifyRequestBuilder = new SpotifyRequestBuilder(REQUEST_URI_STRING);
            spotifyRequestBuilder.queryParam(IDS_QUERY_PARAM, episodeIds);
            addOptionalQueryParams(spotifyRequestBuilder);

            return new GetEpisodes(spotifyRequestBuilder.createGetRequests());
        }

        private void addOptionalQueryParams(SpotifyRequestBuilder requestUriBuilder)
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
