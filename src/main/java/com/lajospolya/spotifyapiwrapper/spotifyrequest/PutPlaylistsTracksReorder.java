package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import com.lajospolya.spotifyapiwrapper.body.PlaylistTrackReorder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.http.HttpRequest;

public class PutPlaylistsTracksReorder extends AbstractSpotifyRequest<Void>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "playlists/{playlist_id}/tracks";

    private PutPlaylistsTracksReorder(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder
    {
        private String id;
        private Integer rangeStart;
        private Integer insertBefore;
        private Integer rangeLength;
        private String snapshotId;

        public Builder(String id, Integer rangeStart, Integer insertBefore) throws IllegalArgumentException
        {
            validateParametersNotNull(id, rangeStart, insertBefore);
            this.id = id;
            this.rangeStart = rangeStart;
            this.insertBefore = insertBefore;
        }

        public PutPlaylistsTracksReorder build()
        {
            UriComponentsBuilder requestUriBuilder =  UriComponentsBuilder.fromUriString(REQUEST_URI_STRING);

            HttpRequest.BodyPublisher bodyPublisher = getBodyPublisher();

            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(requestUriBuilder.buildAndExpand(id).toUri())
                    .header(CONTENT_TYPE_HEADER, APPLICATION_JSON_CONTENT_TYPE_HEADER_VALUE)
                    .PUT(bodyPublisher);
            return new PutPlaylistsTracksReorder(requestBuilder);
        }

        private HttpRequest.BodyPublisher getBodyPublisher()
        {
            return HttpRequest.BodyPublishers.ofString(gson.toJson(
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
