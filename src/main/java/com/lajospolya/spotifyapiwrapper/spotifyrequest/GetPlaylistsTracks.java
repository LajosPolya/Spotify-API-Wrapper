package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import com.lajospolya.spotifyapiwrapper.response.Paging;
import com.lajospolya.spotifyapiwrapper.response.PlaylistTrack;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.http.HttpRequest;

public class GetPlaylistsTracks extends AbstractSpotifyRequest<Paging<PlaylistTrack>>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "playlists/{playlist_id}/tracks";

    private GetPlaylistsTracks(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder
    {
        private String playlistId;
        private Integer limit;
        private Integer offset;
        private String market;
        private String fields;

        public Builder(String playlistId) throws IllegalArgumentException
        {
            validateParametersNotNull(playlistId);
            this.playlistId = playlistId;
        }

        public GetPlaylistsTracks build()
        {
            UriComponentsBuilder requestUriBuilder =  UriComponentsBuilder.fromUriString(REQUEST_URI_STRING);

            addOptionalQueryParams(requestUriBuilder);

            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(requestUriBuilder.buildAndExpand(this.playlistId).toUri())
                    .GET();
            return new GetPlaylistsTracks(requestBuilder);
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
            if(this.fields != null)
            {
                requestUriBuilder.queryParam(FIELDS_QUERY_PARAM, this.fields);
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
