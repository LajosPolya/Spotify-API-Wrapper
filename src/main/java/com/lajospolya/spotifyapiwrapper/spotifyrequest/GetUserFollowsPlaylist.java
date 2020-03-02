package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import org.springframework.web.util.UriComponentsBuilder;

import java.net.http.HttpRequest;
import java.util.List;

public class GetUserFollowsPlaylist extends AbstractSpotifyRequest<List<Boolean>>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "playlists/{playlist_id}/followers/contains";

    private GetUserFollowsPlaylist(HttpRequest.Builder requestBuilder)
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

        public GetUserFollowsPlaylist build()
        {
            UriComponentsBuilder requestUriBuilder =  UriComponentsBuilder.fromUriString(REQUEST_URI_STRING);

            String commaSeparatedIds = String.join(",", this.userIds);

            requestUriBuilder.queryParam(IDS_QUERY_PARAM, commaSeparatedIds);

            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(requestUriBuilder.buildAndExpand(this.playListId).toUri())
                    .GET();
            return new GetUserFollowsPlaylist(requestBuilder);
        }
    }
}
