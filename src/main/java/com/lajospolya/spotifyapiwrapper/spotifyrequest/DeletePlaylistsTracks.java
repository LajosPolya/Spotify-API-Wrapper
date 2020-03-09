package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import com.lajospolya.spotifyapiwrapper.body.TrackIds;
import com.lajospolya.spotifyapiwrapper.response.PlaylistSnapshot;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.http.HttpRequest;
import java.util.List;

public class DeletePlaylistsTracks extends AbstractSpotifyRequest<PlaylistSnapshot>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "playlists/{playlist_id}/tracks";

    private DeletePlaylistsTracks(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder
    {
        private String playlistId;
        private List<String> trackIds;
        private String snapshotId;

        public Builder(String playlistId, List<String> trackIds)
        {
            validateParametersNotNull(playlistId, trackIds);
            spotifyRequestParamValidationService.validateTrackUris(trackIds);
            this.playlistId = playlistId;
            this.trackIds = trackIds;
        }

        public DeletePlaylistsTracks build()
        {
            UriComponentsBuilder requestUriBuilder =  UriComponentsBuilder.fromUriString(REQUEST_URI_STRING);

            HttpRequest.BodyPublisher bodyPublisher = getBodyPublisher();

            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(requestUriBuilder.buildAndExpand(playlistId).toUri())
                    .header(CONTENT_TYPE_HEADER, APPLICATION_JSON_CONTENT_TYPE_HEADER_VALUE)
                    .method(DELETE, bodyPublisher);
            return new DeletePlaylistsTracks(requestBuilder);
        }

        private HttpRequest.BodyPublisher getBodyPublisher()
        {
            return HttpRequest.BodyPublishers.ofString(gson.toJson(new TrackIds(this.trackIds)));
        }

        public Builder snapshotId(String snapshotId)
        {
            this.snapshotId = snapshotId;
            return this;
        }
    }
}
