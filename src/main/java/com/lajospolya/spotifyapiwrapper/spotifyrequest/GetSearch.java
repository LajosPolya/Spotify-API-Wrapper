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
            this.searchItemTypes = searchItemTypes;
            this.query = query;
        }

        public GetSearch build()
        {
            UriComponentsBuilder requestUriBuilder =  UriComponentsBuilder.fromUriString(REQUEST_URI_STRING);

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

            requestUriBuilder.queryParam(LIMIT_QUERY_PARAM, this.limit);
            requestUriBuilder.queryParam(OFFSET_QUERY_PARAM, this.offset);

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
