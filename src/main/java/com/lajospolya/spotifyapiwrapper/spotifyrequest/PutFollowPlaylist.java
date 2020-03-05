package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import com.lajospolya.spotifyapiwrapper.body.PutPlaylistsFollowers;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.http.HttpRequest;

public class PutFollowPlaylist extends AbstractSpotifyRequest<Void>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "playlists/{playlist_id}/followers";

    private PutFollowPlaylist(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder
    {
        private String id;
        private Boolean isPublic = true;

        public Builder(String id)
        {
            validateParametersNotNull(id);
            this.id = id;
        }

        public PutFollowPlaylist build()
        {
            UriComponentsBuilder requestUriBuilder =  UriComponentsBuilder.fromUriString(REQUEST_URI_STRING);

            requestUriBuilder.queryParam(IDS_QUERY_PARAM, this.id);

            HttpRequest.BodyPublisher bodyPublisher = getBodyPublisher();

            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(requestUriBuilder.buildAndExpand(this.id).toUri())
                    .PUT(bodyPublisher);
            return new PutFollowPlaylist(requestBuilder);
        }

        private HttpRequest.BodyPublisher getBodyPublisher()
        {
            if(this.isPublic != null)
            {
                return HttpRequest.BodyPublishers.ofString(gson.toJson(new PutPlaylistsFollowers(isPublic)));
            }
            return HttpRequest.BodyPublishers.noBody();
        }

        public Builder isPublic(Boolean isPublic)
        {
            this.isPublic = isPublic;
            return this;
        }
    }
}
