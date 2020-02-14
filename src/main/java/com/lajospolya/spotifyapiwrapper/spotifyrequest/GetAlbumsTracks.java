package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import com.lajospolya.spotifyapiwrapper.response.Paging;
import com.lajospolya.spotifyapiwrapper.response.SimplifiedTrack;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.http.HttpRequest;

public final class GetAlbumsTracks extends AbstractSpotifyRequest<Paging<SimplifiedTrack>>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "albums/{id}/tracks";

    private HttpRequest request;
    private HttpRequest.Builder requestBuilder;
    private String accessToken;

    private GetAlbumsTracks(HttpRequest.Builder requestBuilder)
    {
        this.requestBuilder = requestBuilder;
    }

    private HttpRequest buildRequest()
    {
        return requestBuilder
                .setHeader(AUTHORIZATION_HEADER, this.accessToken)
                .build();
    }

    public static class Builder extends AbstractBuilder
    {
        private String albumId;
        private Integer limit;
        private Integer offset;
        private String market;

        public Builder(String albumId) throws IllegalArgumentException
        {
            validateParametersNotNull(albumId);
            this.albumId = albumId;
        }

        public GetAlbumsTracks build()
        {
            // Requires param validation
            UriComponentsBuilder requestUriBuilder =  UriComponentsBuilder.fromUriString(REQUEST_URI_STRING);

            addOptionalQueryParams(requestUriBuilder);

            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(requestUriBuilder.buildAndExpand(this.albumId).toUri())
                    .GET();
            return new GetAlbumsTracks(requestBuilder);
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
        }

        public Builder limit(Integer limit)
        {
            if(limit < 1 || limit > 50)
            {
                throw new IllegalArgumentException(ILLEGAL_LIMIT_EXCEPTION_MSG);
            }
            this.limit = limit;
            return this;
        }

        public Builder offset(Integer offset)
        {
            if(offset < 0)
            {
                throw new IllegalArgumentException(ILLEGAL_OFFSET_EXCEPTION_MSG);
            }
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
