package com.lajospolya.spotifyapiwrapper.request;

import com.lajospolya.spotifyapiwrapper.body.PlaylistDetails;
import com.lajospolya.spotifyapiwrapper.component.ISpotifyRequestBuilder;
import com.lajospolya.spotifyapiwrapper.component.SpotifyClientComponentsFactory;

/**
 * @author Lajos Polya
 *
 * Represents the endpoint at PUT https://api.spotify.com/v1/playlists/{playlist_id} as descrbibed at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
public class PutPlaylists extends AbstractSpotifyRequest<Void>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "playlists/{playlist_id}";

    private PutPlaylists(ISpotifyRequestBuilder requestBuilder)
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
            ISpotifyRequestBuilder spotifyRequestBuilder = SpotifyClientComponentsFactory.spotifyRequestBuilder(REQUEST_URI_STRING, playlistsId);

            return createRequest(spotifyRequestBuilder);
        }

        private PutPlaylists createRequest(ISpotifyRequestBuilder spotifyRequestBuilder)
        {
            if(name != null || this.isPublic != null || collaborative != null || description != null)
            {
                return new PutPlaylists(
                        spotifyRequestBuilder.PUTWithJsonBody(
                                new PlaylistDetails(name, isPublic, collaborative, description)));
            }
            return new PutPlaylists(spotifyRequestBuilder.PUT());
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
