package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import java.net.http.HttpRequest;
import java.util.List;

public final class GetMeAlbumsContains extends AbstractSpotifyRequest<List<Boolean>>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "me/albums/contains";

    private GetMeAlbumsContains(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder<GetMeAlbumsContains>
    {
        private List<String> albumIds;

        public Builder(List<String> albumIds) throws IllegalArgumentException
        {
            spotifyRequestParamValidationService.validateParametersNotNull(albumIds);
            spotifyRequestParamValidationService.validateFollowIds(albumIds);
            this.albumIds = albumIds;
        }

        @Override
        public GetMeAlbumsContains build()
        {
            SpotifyRequestBuilder spotifyRequestBuilder = new SpotifyRequestBuilder(REQUEST_URI_STRING);
            spotifyRequestBuilder.queryParam(IDS_QUERY_PARAM, albumIds);

            return new GetMeAlbumsContains(spotifyRequestBuilder.createGetRequests());
        }
    }
}
