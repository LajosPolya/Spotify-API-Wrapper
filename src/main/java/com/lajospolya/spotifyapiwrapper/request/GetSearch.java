package com.lajospolya.spotifyapiwrapper.request;

import com.lajospolya.spotifyapiwrapper.enumeration.ExternalContent;
import com.lajospolya.spotifyapiwrapper.enumeration.SearchItemType;
import com.lajospolya.spotifyapiwrapper.internal.ISpotifyRequestBuilder;
import com.lajospolya.spotifyapiwrapper.internal.SpotifyClientComponentsFactory;
import com.lajospolya.spotifyapiwrapper.response.SearchResults;

import java.util.List;

/**
 * @author Lajos Polya
 *
 * Represents the endpoint at GET https://api.spotify.com/v1/search as descrbibed at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
public class GetSearch extends AbstractSpotifyRequest<SearchResults>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "search";

    private GetSearch(ISpotifyRequestBuilder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder<GetSearch>
    {
        private String query;
        private List<SearchItemType> searchItemTypes;
        private String market;
        private Integer limit;
        private Integer offset;
        private ExternalContent content;

        public Builder(String query, List<SearchItemType> searchItemTypes) throws IllegalArgumentException
        {
            spotifyRequestParamValidationService.validateParametersNotNull(query, searchItemTypes);
            this.searchItemTypes = searchItemTypes;
            this.query = query;
        }

        @Override
        public GetSearch build()
        {
            ISpotifyRequestBuilder spotifyRequestBuilder = SpotifyClientComponentsFactory.spotifyRequestBuilder(REQUEST_URI_STRING);
            spotifyRequestBuilder.queryParam(QUERY, query);
            spotifyRequestBuilder.queryParam(TYPE_QUERY_PARAM, searchItemTypes, SearchItemType::getType);
            addOptionalQueryParams(spotifyRequestBuilder);

            return new GetSearch(spotifyRequestBuilder.GET());
        }

        private void addOptionalQueryParams(ISpotifyRequestBuilder requestUriBuilder)
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
            if(content != null)
            {
                requestUriBuilder.queryParam(INCLUDE_EXTERNAL, content.getContent());
            }
        }

        public GetSearch.Builder market(String market)
        {
            this.market = market;
            return this;
        }

        public GetSearch.Builder limit(Integer limit)
        {
            spotifyRequestParamValidationService.validateLimit50(limit);
            this.limit = limit;
            return this;
        }

        public GetSearch.Builder offset(Integer offset)
        {
            spotifyRequestParamValidationService.validateOffset(offset);
            this.offset = offset;
            return this;
        }

        public GetSearch.Builder includeExternal(ExternalContent content)
        {
            this.content = content;
            return this;
        }
    }
}
