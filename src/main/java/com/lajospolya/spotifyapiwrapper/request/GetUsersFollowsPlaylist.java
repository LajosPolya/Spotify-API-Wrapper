package com.lajospolya.spotifyapiwrapper.request;

import java.net.http.HttpRequest;
import java.util.List;

public final class GetUsersFollowsPlaylist extends AbstractSpotifyRequest<List<Boolean>>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "playlists/{playlist_id}/followers/contains";

    private GetUsersFollowsPlaylist(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder<GetUsersFollowsPlaylist>
    {
        private String playListId;
        private List<String> userIds;

        public Builder(String playListId, List<String> userIds) throws IllegalArgumentException
        {
            spotifyRequestParamValidationService.validateParametersNotNull(playListId, userIds);
            spotifyRequestParamValidationService.validateUserIds(userIds);
            this.playListId = playListId;
            this.userIds = userIds;
        }

        @Override
        public GetUsersFollowsPlaylist build()
        {
            SpotifyRequestBuilder spotifyRequestBuilder = new SpotifyRequestBuilder(REQUEST_URI_STRING, playListId);
            spotifyRequestBuilder.queryParam(IDS_QUERY_PARAM, userIds);

            return new GetUsersFollowsPlaylist(spotifyRequestBuilder.createGetRequests());
        }
    }
}
