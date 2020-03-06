package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import com.lajospolya.spotifyapiwrapper.response.Paging;
import com.lajospolya.spotifyapiwrapper.response.SimplifiedPlaylist;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.http.HttpRequest;

public class GetUsersPlaylists extends AbstractSpotifyRequest<Paging<SimplifiedPlaylist>>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "users/{user_id}/playlists";

    private GetUsersPlaylists(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder
    {
        private String userId;
        private Integer limit;
        private Integer offset;

        public Builder(String userId) throws IllegalArgumentException
        {
            validateParametersNotNull(userId);
            this.userId = userId;
        }

        public GetUsersPlaylists build()
        {
            UriComponentsBuilder requestUriBuilder =  UriComponentsBuilder.fromUriString(REQUEST_URI_STRING);

            addOptionalQueryParams(requestUriBuilder);

            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(requestUriBuilder.buildAndExpand(this.userId).toUri())
                    .GET();
            return new GetUsersPlaylists(requestBuilder);
        }

        private void addOptionalQueryParams(UriComponentsBuilder requestUriBuilder)
        {
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
    }
}
