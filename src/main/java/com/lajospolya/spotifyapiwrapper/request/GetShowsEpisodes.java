package com.lajospolya.spotifyapiwrapper.request;

import com.lajospolya.spotifyapiwrapper.response.Paging;
import com.lajospolya.spotifyapiwrapper.response.SimplifiedEpisode;

import java.net.http.HttpRequest;

/**
 * @author Lajos Polya
 *
 * Represents the endpoint at GET https://api.spotify.com/v1/shows/{id}/episodes as descrbibed at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
public class GetShowsEpisodes extends AbstractSpotifyRequest<Paging<SimplifiedEpisode>>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "shows/{id}/episodes";

    private GetShowsEpisodes(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder<GetShowsEpisodes>
    {
        private String showId;
        private Integer limit;
        private Integer offset;
        private String market;

        public Builder(String showId) throws IllegalArgumentException
        {
            spotifyRequestParamValidationService.validateParametersNotNull(showId);
            this.showId = showId;
        }

        @Override
        public GetShowsEpisodes build()
        {
            SpotifyRequestBuilder spotifyRequestBuilder = new SpotifyRequestBuilder(REQUEST_URI_STRING, showId);
            addOptionalQueryParams(spotifyRequestBuilder);

            return new GetShowsEpisodes(spotifyRequestBuilder.createGetRequests());
        }

        private void addOptionalQueryParams(SpotifyRequestBuilder requestUriBuilder)
        {
            if(market != null)
            {
                requestUriBuilder.queryParam(MARKET_QUERY_PARAM, market);
            }
            if(limit != null)
            {
                requestUriBuilder.queryParam(LIMIT_QUERY_PARAM, limit);
            }
            if(offset != null)
            {
                requestUriBuilder.queryParam(OFFSET_QUERY_PARAM, offset);
            }
        }

        public Builder limit(Integer limit)
        {
            spotifyRequestParamValidationService.validateLimit50(limit);
            this.limit = limit;
            return this;
        }

        public Builder offset(Integer offset)
        {
            spotifyRequestParamValidationService.validateOffset(offset);
            this.offset = offset;
            return this;
        }

        public Builder market(String market)
        {
            this.market = market;
            return this;
        }
    }
}
