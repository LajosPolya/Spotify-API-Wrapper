package com.lajospolya.spotifyapiwrapper.request;

import com.lajospolya.spotifyapiwrapper.response.Image;

import java.util.List;

/**
 * @author Lajos Polya
 *
 * Represents the endpoint at GET https://api.spotify.com/v1/playlists/{playlist_id}/images as descrbibed at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
public class GetPlaylistsImages extends AbstractSpotifyRequest<List<Image>>
{

    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "playlists/{playlist_id}/images";

    private GetPlaylistsImages(SpotifyRequestBuilder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder<GetPlaylistsImages>
    {
        private String playlistId;

        public Builder(String playlistId) throws IllegalArgumentException
        {
            spotifyRequestParamValidationService.validateParametersNotNull(playlistId);
            this.playlistId = playlistId;
        }

        @Override
        public GetPlaylistsImages build()
        {
            SpotifyRequestBuilder spotifyRequestBuilder = new SpotifyRequestBuilder(REQUEST_URI_STRING, playlistId);
            return new GetPlaylistsImages(spotifyRequestBuilder.GET());
        }
    }
}
