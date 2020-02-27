package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import com.lajospolya.spotifyapiwrapper.response.Paging;
import com.lajospolya.spotifyapiwrapper.response.SimplifiedTrack;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.http.HttpRequest;

public final class GetAlbumsTracks extends AbstractSpotifyRequest<Paging<SimplifiedTrack>>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "albums/{id}/tracks";

    private GetAlbumsTracks(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
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
            requestUriBuilder.queryParam(LIMIT_QUERY_PARAM, this.limit);
            requestUriBuilder.queryParam(OFFSET_QUERY_PARAM, this.offset);
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
