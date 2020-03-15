package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import java.net.http.HttpRequest;

public class DeleteFollowPlaylist extends AbstractSpotifyRequest<Void>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "playlists/{playlist_id}/followers";

    private DeleteFollowPlaylist(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder
    {
        private String playlistId;

        public Builder(String playlistId)
        {
            validateParametersNotNull(playlistId);
            this.playlistId = playlistId;
        }

        public DeleteFollowPlaylist build()
        {
            SpotifyRequestBuilder spotifyRequestBuilder = new SpotifyRequestBuilder(REQUEST_URI_STRING, playlistId);

            return new DeleteFollowPlaylist(spotifyRequestBuilder.createDeleteRequest());
        }
    }
}
