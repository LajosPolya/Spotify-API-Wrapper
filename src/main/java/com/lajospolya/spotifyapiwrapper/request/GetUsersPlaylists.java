package com.lajospolya.spotifyapiwrapper.request;

import com.lajospolya.spotifyapiwrapper.component.ISpotifyRequestBuilder;
import com.lajospolya.spotifyapiwrapper.component.SpotifyClientComponentsFactory;
import com.lajospolya.spotifyapiwrapper.response.Paging;
import com.lajospolya.spotifyapiwrapper.response.SimplifiedPlaylist;

/**
 * @author Lajos Polya
 *
 * Represents the endpoint at GET https://api.spotify.com/v1/users/{user_id}/playlists as descrbibed at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
public class GetUsersPlaylists extends AbstractSpotifyRequest<Paging<SimplifiedPlaylist>>
{
    private static final String PATH_PARAM = "{user_id}";
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "users/{user_id}/playlists";

    private GetUsersPlaylists(ISpotifyRequestBuilder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends CacheableRequestBuilder<GetUsersPlaylists>
    {
        private final String userId;
        private Integer limit;
        private Integer offset;

        public Builder(String userId) throws IllegalArgumentException
        {
            spotifyRequestParamValidationService.validateParametersNotNull(userId);
            this.userId = userId;
        }

        @Override
        public GetUsersPlaylists build()
        {
            ISpotifyRequestBuilder spotifyRequestBuilder = SpotifyClientComponentsFactory.spotifyRequestBuilder(REQUEST_URI_STRING);
            spotifyRequestBuilder.pathParam(PATH_PARAM, userId);
            addOptionalQueryParams(spotifyRequestBuilder);
            addEtagHeader(spotifyRequestBuilder);

            return new GetUsersPlaylists(spotifyRequestBuilder.GET());
        }

        private void addOptionalQueryParams(ISpotifyRequestBuilder requestUriBuilder)
        {
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

        @Override
        public Builder etag(String etag)
        {
            this.etag = etag;
            return this;
        }
    }
}
