package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import java.net.http.HttpRequest;
import java.util.List;

public final class GetMeTracksContains extends AbstractSpotifyRequest<List<Boolean>>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "me/tracks/contains";

    private GetMeTracksContains(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder<GetMeTracksContains>
    {
        private List<String> trackIds;

        public Builder(List<String> trackIds) throws IllegalArgumentException
        {
            spotifyRequestParamValidationService.validateParametersNotNull(trackIds);
            spotifyRequestParamValidationService.validateFollowIds(trackIds);
            this.trackIds = trackIds;
        }

        @Override
        public GetMeTracksContains build()
        {
            SpotifyRequestBuilder spotifyRequestBuilder = new SpotifyRequestBuilder(REQUEST_URI_STRING);
            spotifyRequestBuilder.queryParam(IDS_QUERY_PARAM,  trackIds);

            return new GetMeTracksContains(spotifyRequestBuilder.createGetRequests());
        }
    }
}
