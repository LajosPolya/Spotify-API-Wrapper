package com.lajospolya.spotifyapiwrapper.request;

import com.lajospolya.spotifyapiwrapper.body.IdsContainer;

import java.util.List;

/**
 * @author Lajos Polya
 *
 * Represents the endpoint at DELETE https://api.spotify.com/v1/me/albums as descrbibed at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
public class DeleteMeAlbums extends AbstractSpotifyRequest<Void>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "me/albums";

    private DeleteMeAlbums(ISpotifyRequestBuilder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder<DeleteMeAlbums>
    {
        private List<String> albumIds;

        public Builder(List<String> albumIds) throws IllegalArgumentException
        {
            spotifyRequestParamValidationService.validateParametersNotNull(albumIds);
            spotifyRequestParamValidationService.validateIds50(albumIds);
            this.albumIds = albumIds;
        }

        @Override
        public DeleteMeAlbums build()
        {
            ISpotifyRequestBuilder spotifyRequestBuilder = SpotifyClientComponentsFactory.spotifyRequestBuilder(REQUEST_URI_STRING);
            spotifyRequestBuilder.contentType(APPLICATION_JSON_CONTENT_TYPE_HEADER_VALUE);

            return new DeleteMeAlbums(
                    spotifyRequestBuilder.DELETEWithJsonBody(
                            new IdsContainer(albumIds)));
        }
    }
}
