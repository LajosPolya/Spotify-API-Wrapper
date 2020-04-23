package com.lajospolya.spotifyapiwrapper.request;

import com.lajospolya.spotifyapiwrapper.component.ISpotifyRequestBuilder;
import com.lajospolya.spotifyapiwrapper.component.SpotifyClientComponentsFactory;
import com.lajospolya.spotifyapiwrapper.response.Playlist;

/**
 * @author Lajos Polya
 *
 * Represents the endpoint at GET https://api.spotify.com/v1/playlists/{playlist_id} as descrbibed at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
public class GetPlaylist extends AbstractSpotifyRequest<Playlist>
{
    private static final String PATH_PARAM = "{playlist_id}";
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "playlists/{playlist_id}";

    private GetPlaylist(ISpotifyRequestBuilder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends CacheableRequestBuilder<GetPlaylist>
    {
        private final String playlistId;

        public Builder(String playlistId) throws IllegalArgumentException
        {
            spotifyRequestParamValidationService.validateParametersNotNull(playlistId);
            this.playlistId = playlistId;
        }

        @Override
        public GetPlaylist build()
        {
            ISpotifyRequestBuilder spotifyRequestBuilder = SpotifyClientComponentsFactory.spotifyRequestBuilder(REQUEST_URI_STRING)
            .pathParam(PATH_PARAM, playlistId);
            addEtagHeader(spotifyRequestBuilder);
            return new GetPlaylist(spotifyRequestBuilder.GET());
        }

        @Override
        public Builder etag(String etag)
        {
            this.etag = etag;
            return this;
        }
    }
}
