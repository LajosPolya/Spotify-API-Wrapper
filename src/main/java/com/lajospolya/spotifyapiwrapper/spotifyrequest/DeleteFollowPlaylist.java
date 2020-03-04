package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import org.springframework.web.util.UriComponentsBuilder;

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
        private String id;

        public Builder(String id)
        {
            validateParametersNotNull(id);
            this.id = id;
        }

        public DeleteFollowPlaylist build()
        {
            UriComponentsBuilder requestUriBuilder =  UriComponentsBuilder.fromUriString(REQUEST_URI_STRING);

            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(requestUriBuilder.buildAndExpand(id).toUri())
                    .method(DELETE, HttpRequest.BodyPublishers.noBody());
            return new DeleteFollowPlaylist(requestBuilder);
        }
    }
}
