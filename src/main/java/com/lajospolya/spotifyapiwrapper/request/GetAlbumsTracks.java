package com.lajospolya.spotifyapiwrapper.request;

import com.lajospolya.spotifyapiwrapper.response.Paging;
import com.lajospolya.spotifyapiwrapper.response.SimplifiedTrack;

/**
 * @author Lajos Polya
 *
 * Represents the endpoint at GET https://api.spotify.com/v1/albums/{id}/tracks as descrbibed at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
public class GetAlbumsTracks extends AbstractSpotifyRequest<Paging<SimplifiedTrack>>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "albums/{id}/tracks";

    private GetAlbumsTracks(SpotifyRequestBuilder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder<GetAlbumsTracks>
    {
        private String albumId;
        private Integer limit;
        private Integer offset;
        private String market;

        public Builder(String albumId) throws IllegalArgumentException
        {
            spotifyRequestParamValidationService.validateParametersNotNull(albumId);
            this.albumId = albumId;
        }

        @Override
        public GetAlbumsTracks build()
        {
            SpotifyRequestBuilder spotifyRequestBuilder = new SpotifyRequestBuilder(REQUEST_URI_STRING, albumId);
            addOptionalQueryParams(spotifyRequestBuilder);

            return new GetAlbumsTracks(spotifyRequestBuilder.GET());
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
