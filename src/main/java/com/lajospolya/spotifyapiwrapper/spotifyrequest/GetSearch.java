package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import com.lajospolya.spotifyapiwrapper.enumeration.ExternalContent;
import com.lajospolya.spotifyapiwrapper.enumeration.SearchItemType;
import com.lajospolya.spotifyapiwrapper.response.SearchResults;

import java.net.http.HttpRequest;
import java.util.List;

public class GetSearch extends AbstractSpotifyRequest<SearchResults>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "search";

    private GetSearch(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder
    {
        private String query;
        private List<SearchItemType> searchItemTypes;
        private String market;
        private Integer limit;
        private Integer offset;
        private ExternalContent content;

        public Builder(String query, List<SearchItemType> searchItemTypes)
        {
            validateParametersNotNull(query, searchItemTypes);
            this.searchItemTypes = searchItemTypes;
            this.query = query;
        }

        public GetSearch build()
        {
            SpotifyRequestBuilder spotifyRequestBuilder = new SpotifyRequestBuilder(REQUEST_URI_STRING);
            spotifyRequestBuilder.queryParam(QUERY, query);
            spotifyRequestBuilder.queryParam(TYPE_QUERY_PARAM, searchItemTypes, SearchItemType::getType);
            addOptionalQueryParams(spotifyRequestBuilder);

            return new GetSearch(spotifyRequestBuilder.createGetRequests());
        }

        private void addOptionalQueryParams(SpotifyRequestBuilder requestUriBuilder)
        {
            if(this.market != null)
            {
                requestUriBuilder.queryParam(MARKET_QUERY_PARAM, this.market);
            }
            if(this.limit != null)
            {
                requestUriBuilder.queryParam(LIMIT_QUERY_PARAM, this.limit);
            }
            if(this.offset != null)
            {
                requestUriBuilder.queryParam(OFFSET_QUERY_PARAM, this.offset);
            }
            if(this.content != null)
            {
                requestUriBuilder.queryParam(INCLUDE_EXTERNAL, this.content.getContent());
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
