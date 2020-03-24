package com.lajospolya.spotifyapiwrapper.request;

import com.lajospolya.spotifyapiwrapper.body.PlaylistTrackDelete;
import com.lajospolya.spotifyapiwrapper.response.PlaylistSnapshot;

import java.net.http.HttpRequest;
import java.util.List;

public class DeletePlaylistsTracks extends AbstractSpotifyRequest<PlaylistSnapshot>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "playlists/{playlist_id}/tracks";

    private DeletePlaylistsTracks(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder<DeletePlaylistsTracks>
    {
        private String playlistId;
        private List<String> trackIds;
        private String snapshotId;

        public Builder(String playlistId, List<String> trackIds) throws IllegalArgumentException
        {
            spotifyRequestParamValidationService.validateParametersNotNull(playlistId, trackIds);
            spotifyRequestParamValidationService.validateTrackUris(trackIds);
            this.playlistId = playlistId;
            this.trackIds = trackIds;
        }

        @Override
        public DeletePlaylistsTracks build()
        {
            SpotifyRequestBuilder spotifyRequestBuilder = new SpotifyRequestBuilder(REQUEST_URI_STRING, playlistId);
            spotifyRequestBuilder.contentType(APPLICATION_JSON_CONTENT_TYPE_HEADER_VALUE);

            return new DeletePlaylistsTracks(
                    spotifyRequestBuilder.createDeleteRequestWithObjectJsonBody(
                            new PlaylistTrackDelete(trackIds, snapshotId)));
        }

        public Builder snapshotId(String snapshotId)
        {
            this.snapshotId = snapshotId;
            return this;
        }
    }
}
