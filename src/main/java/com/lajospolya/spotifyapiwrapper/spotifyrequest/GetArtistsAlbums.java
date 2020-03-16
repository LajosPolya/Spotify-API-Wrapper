package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import com.lajospolya.spotifyapiwrapper.enumeration.AlbumType;
import com.lajospolya.spotifyapiwrapper.response.ArtistsAlbums;

import java.net.http.HttpRequest;
import java.util.List;

public final class GetArtistsAlbums extends AbstractSpotifyRequest<ArtistsAlbums>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "artists/{id}/albums";

    private GetArtistsAlbums(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder<GetArtistsAlbums>
    {
        private String artistId;
        private Integer limit;
        private Integer offset;
        private String market;
        private List<AlbumType> includeGroups;

        public Builder(String artistId) throws IllegalArgumentException
        {
            spotifyRequestParamValidationService.validateParametersNotNull(artistId);
            this.artistId = artistId;
        }

        @Override
        public GetArtistsAlbums build()
        {
            SpotifyRequestBuilder spotifyRequestBuilder = new SpotifyRequestBuilder(REQUEST_URI_STRING, artistId);
            addOptionalQueryParams(spotifyRequestBuilder);

            return new GetArtistsAlbums(spotifyRequestBuilder.createGetRequests());
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
            if(this.includeGroups != null)
            {
                requestUriBuilder.queryParam(INCLUDE_GROUPS_QUERY_PARAM, this.includeGroups, AlbumType::getType);
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

        public Builder albumType(List<AlbumType> includeGroups)
        {
            spotifyRequestParamValidationService.validateList(includeGroups);
            this.includeGroups = includeGroups;
            return this;
        }
    }
}
