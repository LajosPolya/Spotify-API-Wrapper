package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import com.lajospolya.spotifyapiwrapper.response.Playlist;
import org.springframework.web.util.UriComponentsBuilder;

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
            UriComponentsBuilder requestUriBuilder =  UriComponentsBuilder.fromUriString(REQUEST_URI_STRING);

            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(requestUriBuilder.buildAndExpand(playlistId).toUri())
                    .GET();
            return new GetPlaylist(requestBuilder);
        }
    }
}
