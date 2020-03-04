package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import com.lajospolya.spotifyapiwrapper.enumeration.FollowType;
import com.lajospolya.spotifyapiwrapper.response.Following;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.http.HttpRequest;

public class GetMeFollowing extends AbstractSpotifyRequest<Following>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "me/following";

    private GetMeFollowing(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder
    {
        private FollowType type;
        private Integer limit;
        private String after;

        public Builder(FollowType type)
        {
            validateParametersNotNull(type);
            this.type = type;
        }

        public GetMeFollowing build()
        {
            UriComponentsBuilder requestUriBuilder =  UriComponentsBuilder.fromUriString(REQUEST_URI_STRING);

            requestUriBuilder.queryParam(TYPE_QUERY_PARAM, type.getName());

            addOptionalQueryParams(requestUriBuilder);

            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(requestUriBuilder.build().toUri())
                    .GET();
            return new GetMeFollowing(requestBuilder);
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
