package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import java.net.http.HttpRequest;
import java.util.List;

public class GetUsersFollowsPlaylist extends AbstractSpotifyRequest<List<Boolean>>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "playlists/{playlist_id}/followers/contains";

    private GetUsersFollowsPlaylist(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder
    {
        private String playListId;
        private List<String> userIds;

        public Builder(String playListId, List<String> userIds)
        {
            validateParametersNotNull(playListId, userIds);
            spotifyRequestParamValidationService.validateUserIds(userIds);
            this.playListId = playListId;
            this.userIds = userIds;
        }

        public GetUsersFollowsPlaylist build()
        {
            SpotifyRequestBuilder spotifyRequestBuilder = new SpotifyRequestBuilder(REQUEST_URI_STRING, playListId);
            spotifyRequestBuilder.queryParam(IDS_QUERY_PARAM, userIds);

            return new GetUsersFollowsPlaylist(spotifyRequestBuilder.createGetRequests());
        }
    }
}
