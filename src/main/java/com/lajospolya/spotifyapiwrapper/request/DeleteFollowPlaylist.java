package com.lajospolya.spotifyapiwrapper.request;

import java.net.http.HttpRequest;

/**
 * @author Lajos Polya
 *
 * Represents the endpoint at DELETE https://api.spotify.com/v1/playlists/{playlist_id}/followers as descrbibed at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
public class DeleteFollowPlaylist extends AbstractSpotifyRequest<Void>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "playlists/{playlist_id}/followers";

    private DeleteFollowPlaylist(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder<DeleteFollowPlaylist>
    {
        private String playlistId;

        public Builder(String playlistId) throws IllegalArgumentException
        {
            spotifyRequestParamValidationService.validateParametersNotNull(playlistId);
            this.playlistId = playlistId;
        }

        @Override
        public DeleteFollowPlaylist build()
        {
            SpotifyRequestBuilder spotifyRequestBuilder = new SpotifyRequestBuilder(REQUEST_URI_STRING, playlistId);

            return new DeleteFollowPlaylist(spotifyRequestBuilder.createDeleteRequest());
        }
    }
}
