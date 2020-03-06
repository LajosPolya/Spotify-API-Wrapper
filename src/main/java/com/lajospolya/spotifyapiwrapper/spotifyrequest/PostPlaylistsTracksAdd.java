package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import com.lajospolya.spotifyapiwrapper.body.PlaylistTrackAdd;
import com.lajospolya.spotifyapiwrapper.response.PlaylistSnapshot;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.http.HttpRequest;
import java.util.List;

public class PostPlaylistsTracksAdd extends AbstractSpotifyRequest<PlaylistSnapshot>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "playlists/{playlist_id}/tracks";

    private PostPlaylistsTracksAdd(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder
    {
        private String id;
        private List<String> uris;
        private Integer position;

        public Builder(String id) throws IllegalArgumentException
        {
            validateParametersNotNull(id);
            this.id = id;
        }

        public PostPlaylistsTracksAdd build()
        {
            UriComponentsBuilder requestUriBuilder =  UriComponentsBuilder.fromUriString(REQUEST_URI_STRING);

            HttpRequest.BodyPublisher bodyPublisher = getBodyPublisher();

            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(requestUriBuilder.buildAndExpand(id).toUri())
                    .header(CONTENT_TYPE_HEADER, APPLICATION_JSON_CONTENT_TYPE_HEADER_VALUE)
                    .POST(bodyPublisher);
            return new PostPlaylistsTracksAdd(requestBuilder);
        }

        private HttpRequest.BodyPublisher getBodyPublisher()
        {
            return HttpRequest.BodyPublishers.ofString(gson.toJson(new PlaylistTrackAdd(this.uris, position)));
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
