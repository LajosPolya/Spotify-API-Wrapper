package com.lajospolya.spotifyapiwrapper.request;

import com.lajospolya.spotifyapiwrapper.body.PlaylistTrackReorder;
import com.lajospolya.spotifyapiwrapper.response.PlaylistSnapshot;

/**
 * @author Lajos Polya
 *
 * Represents the endpoint at PUT https://api.spotify.com/v1/playlists/{playlist_id}/tracks as descrbibed at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
public class PutPlaylistsTracksReorder extends AbstractSpotifyRequest<PlaylistSnapshot>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "playlists/{playlist_id}/tracks";

    private PutPlaylistsTracksReorder(ISpotifyRequestBuilder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder<PutPlaylistsTracksReorder>
    {
        private String playlistId;
        private Integer rangeStart;
        private Integer insertBefore;
        private Integer rangeLength;
        private String snapshotId;

        public Builder(String playlistId, Integer rangeStart, Integer insertBefore) throws IllegalArgumentException
        {
            spotifyRequestParamValidationService.validateParametersNotNull(playlistId, rangeStart, insertBefore);
            this.playlistId = playlistId;
            this.rangeStart = rangeStart;
            this.insertBefore = insertBefore;
        }

        @Override
        public PutPlaylistsTracksReorder build()
        {
            ISpotifyRequestBuilder spotifyRequestBuilder = new SpotifyRequestBuilder(REQUEST_URI_STRING, playlistId);
            spotifyRequestBuilder.contentType(APPLICATION_JSON_CONTENT_TYPE_HEADER_VALUE);

            return new PutPlaylistsTracksReorder(
                    spotifyRequestBuilder.PUTWithJsonBody(
                            new PlaylistTrackReorder(rangeStart, insertBefore, rangeLength, snapshotId)));
        }

        public Builder rangeLength(Integer rangeLength)
        {
            this.rangeLength = rangeLength;
            return this;
        }

        public Builder snapshotId(String snapshotId)
        {
            this.snapshotId = snapshotId;
            return this;
        }
    }
}
