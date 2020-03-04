package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import com.lajospolya.spotifyapiwrapper.enumeration.FollowType;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.http.HttpRequest;
import java.util.List;

public class GetMeFollowingContains extends AbstractSpotifyRequest<List<Boolean>>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "me/following/contains";

    private GetMeFollowingContains(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder
    {
        private FollowType type;
        private List<String> ids;

        public Builder(FollowType type, List<String> ids)
        {
            validateParametersNotNull(type, ids);
            this.type = type;
            this.ids = ids;
        }

        public GetMeFollowingContains build()
        {
            UriComponentsBuilder requestUriBuilder =  UriComponentsBuilder.fromUriString(REQUEST_URI_STRING);

            String commaSeparatedIds = String.join(",", this.ids);

            requestUriBuilder.queryParam(IDS_QUERY_PARAM, commaSeparatedIds);
            requestUriBuilder.queryParam(TYPE_QUERY_PARAM, type.getName());

            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(requestUriBuilder.build().toUri())
                    .GET();
            return new GetMeFollowingContains(requestBuilder);
        }
    }
}
