package com.lajospolya.spotifyapiwrapper.request;

import com.lajospolya.spotifyapiwrapper.component.ISpotifyRequestBuilder;
import com.lajospolya.spotifyapiwrapper.component.SpotifyClientComponentsFactory;

/**
 * @author Lajos Polya
 *
 * Represents the endpoint at PUT https://api.spotify.com/v1/playlists/{playlist_id}/images as descrbibed at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
public class PutPlaylistsImages extends AbstractSpotifyRequest<Void>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "playlists/{playlist_id}/images";

    private PutPlaylistsImages(ISpotifyRequestBuilder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder<PutPlaylistsImages>
    {
        private final String playlistId;
        private final String base64image;

        public Builder(String playlistId, String base64image) throws IllegalArgumentException
        {
            spotifyRequestParamValidationService.validateParametersNotNull(playlistId, base64image);
            this.playlistId = playlistId;
            // TODO: Restring image size to 256KB if possible
            this.base64image = base64image;
        }

        @Override
        public PutPlaylistsImages build()
        {
            ISpotifyRequestBuilder spotifyRequestBuilder = SpotifyClientComponentsFactory.spotifyRequestBuilder(REQUEST_URI_STRING, playlistId);
            spotifyRequestBuilder.contentType(IMAGE_JPEG_HEADER_VALUE);

            return new PutPlaylistsImages(spotifyRequestBuilder.PUTWithStringBody(base64image));
        }
    }
}
