package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import com.lajospolya.spotifyapiwrapper.response.Paging;
import com.lajospolya.spotifyapiwrapper.response.PlaylistTrack;

import java.net.http.HttpRequest;

public final class GetPlaylistsTracks extends AbstractSpotifyRequest<Paging<PlaylistTrack>>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "playlists/{playlist_id}/tracks";

    private GetPlaylistsTracks(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder<GetPlaylistsTracks>
    {
        private String playlistId;
        private Integer limit;
        private Integer offset;
        private String market;
        private String fields;

        public Builder(String playlistId) throws IllegalArgumentException
        {
            spotifyRequestParamValidationService.validateParametersNotNull(playlistId);
            this.playlistId = playlistId;
        }

        @Override
        public GetPlaylistsTracks build()
        {
            SpotifyRequestBuilder spotifyRequestBuilder = new SpotifyRequestBuilder(REQUEST_URI_STRING, this.playlistId);
            addOptionalQueryParams(spotifyRequestBuilder);

            return new GetPlaylistsTracks(spotifyRequestBuilder.createGetRequests());
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
            if(fields != null)
            {
                requestUriBuilder.queryParam(FIELDS_QUERY_PARAM, fields);
            }
        }

        public Builder limit(Integer limit)
        {
            spotifyRequestParamValidationService.validateLimit100(limit);
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

        public Builder fields(String fields)
        {
            this.fields = fields;
            return this;
        }
    }
}
