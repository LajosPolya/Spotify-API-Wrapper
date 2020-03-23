package com.lajospolya.spotifyapiwrapper.request;

import com.lajospolya.spotifyapiwrapper.body.PlaylistDetails;

import java.net.http.HttpRequest;

public final class PutPlaylists extends AbstractSpotifyRequest<Void>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "playlists/{playlist_id}";

    private PutPlaylists(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder<PutPlaylists>
    {
        private String playlistsId;
        private String name;
        private Boolean isPublic;
        private Boolean collaborative;
        private String description;

        public Builder(String playlistsId) throws IllegalArgumentException
        {
            spotifyRequestParamValidationService.validateParametersNotNull(playlistsId);
            this.playlistsId = playlistsId;
        }

        @Override
        public PutPlaylists build()
        {
            SpotifyRequestBuilder spotifyRequestBuilder = new SpotifyRequestBuilder(REQUEST_URI_STRING, playlistsId);

            return createRequest(spotifyRequestBuilder);
        }

        private PutPlaylists createRequest(SpotifyRequestBuilder spotifyRequestBuilder)
        {
            if(name != null || this.isPublic != null || collaborative != null || description != null)
            {
                return new PutPlaylists(
                        spotifyRequestBuilder.createPutRequestWithObjectJsonBody(
                                new PlaylistDetails(name, isPublic, collaborative, description)));
            }
            return new PutPlaylists(spotifyRequestBuilder.createPutRequest());
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
