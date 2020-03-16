package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import com.lajospolya.spotifyapiwrapper.enumeration.FollowType;
import com.lajospolya.spotifyapiwrapper.response.Following;

import java.net.http.HttpRequest;

public final class GetMeFollowing extends AbstractSpotifyRequest<Following>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "me/following";

    private GetMeFollowing(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder<GetMeFollowing>
    {
        private FollowType type;
        private Integer limit;
        private String after;

        public Builder(FollowType type) throws IllegalArgumentException
        {
            spotifyRequestParamValidationService.validateParametersNotNull(type);
            this.type = type;
        }

        @Override
        public GetMeFollowing build()
        {
            SpotifyRequestBuilder spotifyRequestBuilder = new SpotifyRequestBuilder(REQUEST_URI_STRING);
            spotifyRequestBuilder.queryParam(TYPE_QUERY_PARAM, type.getName());
            addOptionalQueryParams(spotifyRequestBuilder);

            return new GetMeFollowing(spotifyRequestBuilder.createGetRequests());
        }

        private void addOptionalQueryParams(SpotifyRequestBuilder requestUriBuilder)
        {
            if(this.limit != null)
            {
                requestUriBuilder.queryParam(LIMIT_QUERY_PARAM, this.limit);
            }
            if(this.after != null)
            {
                requestUriBuilder.queryParam(AFTER_QUERY_PARAM, this.after);
            }
        }

        public Builder limit(Integer limit)
        {
            spotifyRequestParamValidationService.validateLimit50(limit);
            this.limit = limit;
            return this;
        }

        public Builder after(String after)
        {
            this.after = after;
            return this;
        }
    }
}
