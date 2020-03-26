package com.lajospolya.spotifyapiwrapper.request;

import com.lajospolya.spotifyapiwrapper.response.Playlist;

import java.net.http.HttpRequest;

public class GetPlaylist extends AbstractSpotifyRequest<Playlist>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "playlists/{playlist_id}";

    private GetPlaylist(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends CacheableRequestBuilder<GetPlaylist>
    {
        private String playlistId;

        public Builder(String playlistId) throws IllegalArgumentException
        {
            spotifyRequestParamValidationService.validateParametersNotNull(playlistId);
            this.playlistId = playlistId;
        }

        @Override
        public GetPlaylist build()
        {
            SpotifyRequestBuilder spotifyRequestBuilder = new SpotifyRequestBuilder(REQUEST_URI_STRING, playlistId);
            addEtagHeader(spotifyRequestBuilder);
            return new GetPlaylist(spotifyRequestBuilder.createGetRequests());
        }

        @Override
        public Builder etag(String etag)
        {
            this.etag = etag;
            return this;
        }
    }
}
