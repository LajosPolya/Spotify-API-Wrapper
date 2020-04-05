package com.lajospolya.spotifyapiwrapper.request;

import com.lajospolya.spotifyapiwrapper.body.UrisContainer;

import java.util.List;

/**
 * @author Lajos Polya
 *
 * Represents the endpoint at PUT https://api.spotify.com/v1/playlists/{playlist_id}/tracks as descrbibed at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
public class PutPlaylistsTracks extends AbstractSpotifyRequest<Void>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "playlists/{playlist_id}/tracks";

    private PutPlaylistsTracks(SpotifyRequestBuilder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder<PutPlaylistsTracks>
    {
        private String playlistId;
        private List<String> uris;

        public Builder(String playlistId, List<String> uris) throws IllegalArgumentException
        {
            spotifyRequestParamValidationService.validateParametersNotNull(playlistId, uris);
            spotifyRequestParamValidationService.validatePlaylistUris(uris);
            this.playlistId = playlistId;
            this.uris = uris;
        }

        @Override
        public PutPlaylistsTracks build()
        {
            SpotifyRequestBuilder spotifyRequestBuilder = new SpotifyRequestBuilder(REQUEST_URI_STRING, playlistId);
            spotifyRequestBuilder.contentType(APPLICATION_JSON_CONTENT_TYPE_HEADER_VALUE);

            return new PutPlaylistsTracks(
                    spotifyRequestBuilder.PUTWithJsonBody(
                            new UrisContainer(uris)));
        }
    }
}
