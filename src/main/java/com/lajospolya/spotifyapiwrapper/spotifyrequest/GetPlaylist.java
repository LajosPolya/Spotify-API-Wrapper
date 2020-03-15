package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import com.lajospolya.spotifyapiwrapper.response.Playlist;

import java.net.http.HttpRequest;

public class GetPlaylist extends AbstractSpotifyRequest<Playlist>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "playlists/{playlist_id}";

    private GetPlaylist(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder
    {
        private String playlistId;

        public Builder(String playlistId) throws IllegalArgumentException
        {
            validateParametersNotNull(playlistId);
            this.playlistId = playlistId;
        }

        public GetPlaylist build()
        {
            SpotifyRequestBuilder spotifyRequestBuilder = new SpotifyRequestBuilder(REQUEST_URI_STRING, playlistId);
            return new GetPlaylist(spotifyRequestBuilder.createGetRequests());
        }
    }
}
