package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import com.lajospolya.spotifyapiwrapper.enumeration.ExternalContent;
import com.lajospolya.spotifyapiwrapper.enumeration.SearchItemType;
import com.lajospolya.spotifyapiwrapper.response.SearchResults;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.http.HttpRequest;
import java.util.List;
import java.util.stream.Collectors;

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
            this.query = query;
            this.searchItemTypes = searchItemTypes;
        }

        public GetSearch build()
        {
            UriComponentsBuilder requestUriBuilder =  UriComponentsBuilder.fromUriString(REQUEST_URI_STRING);

            query = query.replace(" ", "%20");
            requestUriBuilder.queryParam(QUERY, query);

            String commaSearchItemTypes = this.searchItemTypes.stream()
                    .map(SearchItemType::getType)
                    .collect(Collectors.joining(","));
            requestUriBuilder.queryParam(SEARCH_ALBUM_TYPE, commaSearchItemTypes);

            addOptionalQueryParams(requestUriBuilder);

            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(requestUriBuilder.build().toUri())
                    .GET();
            return new GetSearch(requestBuilder);
        }

        private void addOptionalQueryParams(UriComponentsBuilder requestUriBuilder)
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
            if(limit < 1 || limit > 50)
            {
                throw new IllegalArgumentException(ILLEGAL_LIMIT_EXCEPTION_MSG);
            }
            this.limit = limit;
            return this;
        }

        public GetSearch.Builder offset(Integer offset)
        {
            if(offset < 0)
            {
                throw new IllegalArgumentException(ILLEGAL_OFFSET_EXCEPTION_MSG);
            }
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
