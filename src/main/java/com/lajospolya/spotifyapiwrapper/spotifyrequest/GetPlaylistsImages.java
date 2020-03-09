package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import com.lajospolya.spotifyapiwrapper.response.Image;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.http.HttpRequest;
import java.util.List;

public class GetPlaylistsImages extends AbstractSpotifyRequest<List<Image>>
{

    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "playlists/{playlist_id}/images";

    private GetPlaylistsImages(HttpRequest.Builder requestBuilder)
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

        public GetPlaylistsImages build()
        {
            UriComponentsBuilder requestUriBuilder =  UriComponentsBuilder.fromUriString(REQUEST_URI_STRING);

            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(requestUriBuilder.buildAndExpand(playlistId).toUri())
                    .GET();
            return new GetPlaylistsImages(requestBuilder);
        }
    }
}
