package com.lajospolya.spotifyapiwrapper.request;

import com.lajospolya.spotifyapiwrapper.component.ISpotifyRequestBuilder;
import com.lajospolya.spotifyapiwrapper.component.SpotifyClientComponentsFactory;

import java.util.List;

/**
 * @author Lajos Polya
 *
 * Represents the endpoint at GET https://api.spotify.com/v1/playlists/{playlist_id}/followers/contains as descrbibed at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
public class GetUsersFollowsPlaylist extends AbstractSpotifyRequest<List<Boolean>>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "playlists/{playlist_id}/followers/contains";

    private GetUsersFollowsPlaylist(ISpotifyRequestBuilder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder<GetUsersFollowsPlaylist>
    {
        private final String playListId;
        private final List<String> userIds;

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
            ISpotifyRequestBuilder spotifyRequestBuilder = SpotifyClientComponentsFactory.spotifyRequestBuilder(REQUEST_URI_STRING, playListId);
            spotifyRequestBuilder.queryParam(IDS_QUERY_PARAM, userIds);

            return new GetUsersFollowsPlaylist(spotifyRequestBuilder.GET());
        }
    }
}
