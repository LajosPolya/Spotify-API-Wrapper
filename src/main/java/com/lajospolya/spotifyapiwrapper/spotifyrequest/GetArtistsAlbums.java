package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import com.lajospolya.spotifyapiwrapper.enumeration.AlbumType;
import com.lajospolya.spotifyapiwrapper.response.ArtistsAlbums;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.http.HttpRequest;
import java.util.List;
import java.util.stream.Collectors;

public final class GetArtistsAlbums extends AbstractSpotifyRequest<ArtistsAlbums>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "artists/{id}/albums";

    private GetArtistsAlbums(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder
    {
        private String artistId;
        private Integer limit;
        private Integer offset;
        private String market;
        private List<AlbumType> includeGroups;

        public Builder(String artistId) throws IllegalArgumentException
        {
            validateParametersNotNull(artistId);
            this.artistId = artistId;
        }

        public GetArtistsAlbums build()
        {
            UriComponentsBuilder requestUriBuilder =  UriComponentsBuilder.fromUriString(REQUEST_URI_STRING);

            addOptionalQueryParams(requestUriBuilder);

            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(requestUriBuilder.buildAndExpand(this.artistId).toUri())
                    .GET();
            return new GetArtistsAlbums(requestBuilder);
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
            if(this.includeGroups != null)
            {
                String commaSeparatedAlbumType = this.includeGroups
                        .stream()
                        .map(AlbumType::getType)
                        .collect(Collectors.joining(","));
                requestUriBuilder.queryParam(IDS_QUERY_PARAM, commaSeparatedAlbumType);
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
