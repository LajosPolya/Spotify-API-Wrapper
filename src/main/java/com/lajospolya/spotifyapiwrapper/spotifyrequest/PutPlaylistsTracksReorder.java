package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import com.lajospolya.spotifyapiwrapper.body.PlaylistTrackReorder;
import com.lajospolya.spotifyapiwrapper.response.PlaylistSnapshot;

import java.net.http.HttpRequest;

public class PutPlaylistsTracksReorder extends AbstractSpotifyRequest<PlaylistSnapshot>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "playlists/{playlist_id}/tracks";

    private PutPlaylistsTracksReorder(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder
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

        public PutPlaylistsTracksReorder build()
        {
            SpotifyRequestBuilder spotifyRequestBuilder = new SpotifyRequestBuilder(REQUEST_URI_STRING, playlistId);
            spotifyRequestBuilder.header(CONTENT_TYPE_HEADER, APPLICATION_JSON_CONTENT_TYPE_HEADER_VALUE);

            return new PutPlaylistsTracksReorder(
                    spotifyRequestBuilder.createPutRequestWithObjectJsonBody(
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
