package com.lajospolya.spotifyapiwrapper.request;

import com.lajospolya.spotifyapiwrapper.body.PlaylistTrackAdd;
import com.lajospolya.spotifyapiwrapper.response.PlaylistSnapshot;

import java.net.http.HttpRequest;
import java.util.List;

/**
 * @author Lajos Polya
 *
 * Represents the endpoint at POST https://api.spotify.com/v1/playlists/{playlist_id}/tracks as descrbibed at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
public class PostPlaylistsTracksAdd extends AbstractSpotifyRequest<PlaylistSnapshot>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "playlists/{playlist_id}/tracks";

    private PostPlaylistsTracksAdd(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder<PostPlaylistsTracksAdd>
    {
        private String playlistId;
        private List<String> uris;
        private Integer position;

        public Builder(String playlistId) throws IllegalArgumentException
        {
            spotifyRequestParamValidationService.validateParametersNotNull(playlistId);
            this.playlistId = playlistId;
        }

        @Override
        public PostPlaylistsTracksAdd build()
        {
            SpotifyRequestBuilder spotifyRequestBuilder = new SpotifyRequestBuilder(REQUEST_URI_STRING, playlistId);
            spotifyRequestBuilder.contentType(APPLICATION_JSON_CONTENT_TYPE_HEADER_VALUE);

            return new PostPlaylistsTracksAdd(
                    spotifyRequestBuilder.POSTWithJsonBody(
                            new PlaylistTrackAdd(uris, position)));
        }

        public Builder uris(List<String> uris) throws IllegalArgumentException
        {
            spotifyRequestParamValidationService.validatePlaylistUris(uris);
            this.uris = uris;
            return this;
        }

        public Builder position(Integer position)
        {
            this.position = position;
            return this;
        }
    }
}
