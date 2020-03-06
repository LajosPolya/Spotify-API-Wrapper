package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import com.lajospolya.spotifyapiwrapper.body.PlaylistDetails;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.http.HttpRequest;

public class PutPlaylists extends AbstractSpotifyRequest<Void>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "playlists/{playlist_id}";

    private PutPlaylists(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder
    {
        private String id;
        private String name;
        private Boolean isPublic;
        private Boolean collaborative;
        private String description;

        public Builder(String id)
        {
            validateParametersNotNull(id);
            this.id = id;
        }

        public PutPlaylists build()
        {
            UriComponentsBuilder requestUriBuilder =  UriComponentsBuilder.fromUriString(REQUEST_URI_STRING);

            HttpRequest.BodyPublisher bodyPublisher = getBodyPublisher();

            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(requestUriBuilder.buildAndExpand(this.id).toUri())
                    .PUT(bodyPublisher);
            return new PutPlaylists(requestBuilder);
        }

        private HttpRequest.BodyPublisher getBodyPublisher()
        {
            if(name != null || this.isPublic != null || collaborative != null || description != null)
            {
                return HttpRequest.BodyPublishers.ofString(gson.toJson(new PlaylistDetails(name, isPublic, collaborative, description)));
            }
            return HttpRequest.BodyPublishers.noBody();
        }

        public Builder isPublic(Boolean isPublic)
        {
            this.isPublic = isPublic;
            return this;
        }

        public Builder name(String name)
        {
            this.name = name;
            return this;
        }

        public Builder collaborative(Boolean collaborative)
        {
            this.collaborative = collaborative;
            return this;
        }

        public Builder description(String description)
        {
            this.description = description;
            return this;
        }
    }
}
