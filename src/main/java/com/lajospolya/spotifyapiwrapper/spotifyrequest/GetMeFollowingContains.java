package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import com.lajospolya.spotifyapiwrapper.enumeration.FollowType;

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
            spotifyRequestParamValidationService.validateFollowIds(ids);
            this.type = type;
            this.ids = ids;
        }

        public GetMeFollowingContains build()
        {
            SpotifyRequestBuilder spotifyRequestBuilder = new SpotifyRequestBuilder(REQUEST_URI_STRING);
            spotifyRequestBuilder.queryParam(IDS_QUERY_PARAM, ids);
            spotifyRequestBuilder.queryParam(TYPE_QUERY_PARAM, type.getName());

            return new GetMeFollowingContains(spotifyRequestBuilder.createGetRequests());
        }
    }
}
