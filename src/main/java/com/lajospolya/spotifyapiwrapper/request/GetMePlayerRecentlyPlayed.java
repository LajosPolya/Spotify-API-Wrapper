package com.lajospolya.spotifyapiwrapper.request;

import com.lajospolya.spotifyapiwrapper.component.ISpotifyRequestBuilder;
import com.lajospolya.spotifyapiwrapper.component.SpotifyClientComponentsFactory;
import com.lajospolya.spotifyapiwrapper.response.PagingCursor;
import com.lajospolya.spotifyapiwrapper.response.PlayHistory;

/**
 * @author Lajos Polya
 *
 * Represents the endpoint at GET https://api.spotify.com/v1/me/player/recently-played as descrbibed at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
public class GetMePlayerRecentlyPlayed extends AbstractSpotifyRequest<PagingCursor<PlayHistory>>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "me/player/recently-played";

    private GetMePlayerRecentlyPlayed(ISpotifyRequestBuilder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder<GetMePlayerRecentlyPlayed>
    {
        private Integer limit;
        private Long after;
        private Long before;

        public Builder() { }

        @Override
        public GetMePlayerRecentlyPlayed build()
        {
            ISpotifyRequestBuilder spotifyRequestBuilder = SpotifyClientComponentsFactory.spotifyRequestBuilder(REQUEST_URI_STRING);
            addOptionalQueryParams(spotifyRequestBuilder);

            return new GetMePlayerRecentlyPlayed(spotifyRequestBuilder.GET());
        }

        private void addOptionalQueryParams(ISpotifyRequestBuilder requestUriBuilder)
        {
            if(limit != null)
            {
                requestUriBuilder.queryParam(LIMIT_QUERY_PARAM, limit);
            }
            if(after != null)
            {
                requestUriBuilder.queryParam(AFTER_QUERY_PARAM, after);
            }
            if(before != null)
            {
                requestUriBuilder.queryParam(BEFORE_QUERY_PARAM, before);
            }
        }

        public Builder limit(Integer limit)
        {
            spotifyRequestParamValidationService.validateLimit50(limit);
            this.limit = limit;
            return this;
        }

        public Builder after(Long after)
        {
            if(after != null && before != null)
            {
                throw new IllegalArgumentException("after and before cannot both be set.");
            }
            this.after = after;
            return this;
        }

        public Builder before(Long before)
        {
            if(before != null && after != null)
            {
                throw new IllegalArgumentException("after and before cannot both be set.");
            }
            this.before = before;
            return this;
        }
    }
}
