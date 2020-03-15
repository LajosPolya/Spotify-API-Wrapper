package com.lajospolya.spotifyapiwrapper.spotifyrequest;

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

        public Builder(String playlistId, String base64image) throws IllegalArgumentException
        {
            validateParametersNotNull(playlistId, base64image);
            this.playlistId = playlistId;
            // TODO: Restring image size to 256KB if possible
            this.base64image = base64image;
        }

        public PutPlaylistsImages build()
        {
            SpotifyRequestBuilder spotifyRequestBuilder = new SpotifyRequestBuilder(REQUEST_URI_STRING, playlistId);
            spotifyRequestBuilder.header(CONTENT_TYPE_HEADER, IMAGE_JPEG_HEADER_VALUE);

            return new PutPlaylistsImages(spotifyRequestBuilder.createPutRequestWithStringBody(base64image));
        }
    }
}
