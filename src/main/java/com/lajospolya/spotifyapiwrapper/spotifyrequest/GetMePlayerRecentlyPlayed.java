package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import com.lajospolya.spotifyapiwrapper.response.PagingCursor;
import com.lajospolya.spotifyapiwrapper.response.PlayHistory;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.http.HttpRequest;

public class GetMePlayerRecentlyPlayed extends AbstractSpotifyRequest<PagingCursor<PlayHistory>>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "me/player/recently-played";

    private GetMePlayerRecentlyPlayed(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder
    {
        private Integer limit;
        private Long after;
        private Long before;

        public Builder() { }

        public GetMePlayerRecentlyPlayed build()
        {
            UriComponentsBuilder requestUriBuilder =  UriComponentsBuilder.fromUriString(REQUEST_URI_STRING);

            addOptionalQueryParams(requestUriBuilder);

            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(requestUriBuilder.build().toUri())
                    .GET();
            return new GetMePlayerRecentlyPlayed(requestBuilder);
        }

        private void addOptionalQueryParams(UriComponentsBuilder requestUriBuilder)
        {
            if(this.limit != null)
            {
                requestUriBuilder.queryParam(LIMIT_QUERY_PARAM, this.limit);
            }
            if(this.after != null)
            {
                requestUriBuilder.queryParam(AFTER_QUERY_PARAM, this.after);
            }
            if(this.before != null)
            {
                requestUriBuilder.queryParam(BEFORE_QUERY_PARAM, this.before);
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
            if(after != null && this.before != null)
            {
                throw new IllegalArgumentException("after and before cannot both be set.");
            }
            this.after = after;
            return this;
        }

        public Builder before(Long before)
        {
            if(before != null && this.after != null)
            {
                throw new IllegalArgumentException("after and before cannot both be set.");
            }
            this.before = before;
            return this;
        }
    }
}
