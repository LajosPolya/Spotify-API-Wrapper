package com.lajospolya.spotifyapiwrapper.request;

import com.lajospolya.spotifyapiwrapper.body.PutPlaylistsFollowers;

/**
 * @author Lajos Polya
 *
 * Represents the endpoint at PUT https://api.spotify.com/v1/playlists/{playlist_id}/followers as descrbibed at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
public class PutFollowPlaylist extends AbstractSpotifyRequest<Void>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "playlists/{playlist_id}/followers";

    private PutFollowPlaylist(SpotifyRequestBuilder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder<PutFollowPlaylist>
    {
        private String playlistId;
        private Boolean isPublic = true;

        public Builder(String playlistId) throws IllegalArgumentException
        {
            spotifyRequestParamValidationService.validateParametersNotNull(playlistId);
            this.playlistId = playlistId;
        }

        @Override
        public PutFollowPlaylist build()
        {
            SpotifyRequestBuilder spotifyRequestBuilder = new SpotifyRequestBuilder(REQUEST_URI_STRING, playlistId);

            return new PutFollowPlaylist(createRequest(spotifyRequestBuilder));
        }

        private SpotifyRequestBuilder createRequest(SpotifyRequestBuilder spotifyRequestBuilder)
        {
            if(isPublic != null)
            {
                return spotifyRequestBuilder.PUTWithJsonBody(new PutPlaylistsFollowers(isPublic));
            }
            return spotifyRequestBuilder.PUT();
        }

        public Builder isPublic(Boolean isPublic)
        {
            this.isPublic = isPublic;
            return this;
        }
    }
}
