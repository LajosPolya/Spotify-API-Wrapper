package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import com.lajospolya.spotifyapiwrapper.body.UrisContainer;

import java.net.http.HttpRequest;
import java.util.List;

public final class PutPlaylistsTracks extends AbstractSpotifyRequest<Void>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "playlists/{playlist_id}/tracks";

    private PutPlaylistsTracks(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder
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

        public PutPlaylistsTracks build()
        {
            SpotifyRequestBuilder spotifyRequestBuilder = new SpotifyRequestBuilder(REQUEST_URI_STRING, playlistId);
            spotifyRequestBuilder.header(CONTENT_TYPE_HEADER, APPLICATION_JSON_CONTENT_TYPE_HEADER_VALUE);

            return new PutPlaylistsTracks(
                    spotifyRequestBuilder.createPutRequestWithObjectJsonBody(
                            new UrisContainer(this.uris)));
        }
    }
}
