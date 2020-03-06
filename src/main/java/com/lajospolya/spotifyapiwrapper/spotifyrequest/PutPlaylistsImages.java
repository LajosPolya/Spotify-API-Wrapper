package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import org.springframework.web.util.UriComponentsBuilder;

import java.net.http.HttpRequest;

public class PutPlaylistsImages extends AbstractSpotifyRequest<Void>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "playlists/{playlist_id}/images";

    private PutPlaylistsImages(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder
    {
        private String playlistId;
        private String base64image;

        public Builder(String playlistId, String base64image)
        {
            validateParametersNotNull(playlistId, base64image);
            this.playlistId = playlistId;
            this.base64image = base64image;
        }

        public PutPlaylistsImages build()
        {
            UriComponentsBuilder requestUriBuilder =  UriComponentsBuilder.fromUriString(REQUEST_URI_STRING);

            HttpRequest.BodyPublisher bodyPublisher = getBodyPublisher();

            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(requestUriBuilder.buildAndExpand(playlistId).toUri())
                    .header(CONTENT_TYPE_HEADER, IMAGE_JPEG_HEADER_VALUE)
                    .PUT(bodyPublisher);
            return new PutPlaylistsImages(requestBuilder);
        }

        private HttpRequest.BodyPublisher getBodyPublisher()
        {
            return HttpRequest.BodyPublishers.ofString(base64image);
        }
    }
}
