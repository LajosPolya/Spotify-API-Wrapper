package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import com.lajospolya.spotifyapiwrapper.body.PutPlaylistsFollowers;

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
        private String playlistId;
        private Boolean isPublic = true;

        public Builder(String playlistId) throws IllegalArgumentException
        {
            spotifyRequestParamValidationService.validateParametersNotNull(playlistId);
            this.playlistId = playlistId;
        }

        public PutFollowPlaylist build()
        {
            SpotifyRequestBuilder spotifyRequestBuilder = new SpotifyRequestBuilder(REQUEST_URI_STRING, playlistId);

            return new PutFollowPlaylist(createRequest(spotifyRequestBuilder));
        }

        private HttpRequest.Builder createRequest(SpotifyRequestBuilder spotifyRequestBuilder)
        {
            if(this.isPublic != null)
            {
                return spotifyRequestBuilder.createPutRequestWithObjectJsonBody(new PutPlaylistsFollowers(isPublic));
            }
            return spotifyRequestBuilder.createPutRequest();
        }

        public Builder isPublic(Boolean isPublic)
        {
            this.isPublic = isPublic;
            return this;
        }
    }
}
