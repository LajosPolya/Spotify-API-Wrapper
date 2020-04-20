package com.lajospolya.spotifyapiwrapper.request;

import com.lajospolya.spotifyapiwrapper.body.IdsContainer;
import com.lajospolya.spotifyapiwrapper.component.ISpotifyRequestBuilder;
import com.lajospolya.spotifyapiwrapper.component.SpotifyClientComponentsFactory;

import java.util.List;

/**
 * @author Lajos Polya
 *
 * Represents the endpoint at PUT https://api.spotify.com/v1/me/albums as descrbibed at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
public class PutMeAlbums extends AbstractSpotifyRequest<Void>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "me/albums";

    private PutMeAlbums(ISpotifyRequestBuilder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder<PutMeAlbums>
    {
        private final List<String> albumIds;

        public Builder(List<String> albumIds) throws IllegalArgumentException
        {
            spotifyRequestParamValidationService.validateParametersNotNull(albumIds);
            spotifyRequestParamValidationService.validateIds50(albumIds);
            this.albumIds = albumIds;
        }

        @Override
        public PutMeAlbums build()
        {
            ISpotifyRequestBuilder spotifyRequestBuilder = SpotifyClientComponentsFactory.spotifyRequestBuilder(REQUEST_URI_STRING);
            spotifyRequestBuilder.contentType(APPLICATION_JSON_CONTENT_TYPE_HEADER_VALUE);

            return new PutMeAlbums(
                    spotifyRequestBuilder.PUTWithJsonBody(
                            new IdsContainer(albumIds)));
        }
    }
}
