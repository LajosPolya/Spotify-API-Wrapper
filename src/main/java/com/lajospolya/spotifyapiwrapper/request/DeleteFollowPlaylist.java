package com.lajospolya.spotifyapiwrapper.request;

import com.lajospolya.spotifyapiwrapper.component.ISpotifyRequestBuilder;
import com.lajospolya.spotifyapiwrapper.component.SpotifyClientComponentsFactory;

/**
 * @author Lajos Polya
 *
 * Represents the endpoint at DELETE https://api.spotify.com/v1/playlists/{playlist_id}/followers as descrbibed at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
public class DeleteFollowPlaylist extends AbstractSpotifyRequest<Void>
{
    private static final String PATH_PARAM = "{playlist_id}";
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "playlists/{playlist_id}/followers";

    private DeleteFollowPlaylist(ISpotifyRequestBuilder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder<DeleteFollowPlaylist>
    {
        private final String playlistId;

        public Builder(String playlistId) throws IllegalArgumentException
        {
            spotifyRequestParamValidationService.validateParametersNotNull(playlistId);
            this.playlistId = playlistId;
        }

        @Override
        public DeleteFollowPlaylist build()
        {
            ISpotifyRequestBuilder spotifyRequestBuilder = SpotifyClientComponentsFactory.spotifyRequestBuilder(REQUEST_URI_STRING)
            .pathParam(PATH_PARAM, playlistId);

            return new DeleteFollowPlaylist(spotifyRequestBuilder.DELETE());
        }
    }
}
