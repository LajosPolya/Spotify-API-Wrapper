package com.lajospolya.spotifyapiwrapper.request;

import com.lajospolya.spotifyapiwrapper.component.ISpotifyRequestBuilder;
import com.lajospolya.spotifyapiwrapper.component.SpotifyClientComponentsFactory;
import com.lajospolya.spotifyapiwrapper.response.Album;

/**
 * @author Lajos Polya
 *
 * Represents the endpoint at GET https://api.spotify.com/v1/albums/{id} as descrbibed at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
public class GetAlbum extends AbstractSpotifyRequest<Album>
{
    private static final String PATH_PARAM = "{id}";
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "albums/{id}";

    private GetAlbum(ISpotifyRequestBuilder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder<GetAlbum>
    {
        private final String albumId;
        private String market;

        public Builder(String albumId) throws IllegalArgumentException
        {
            spotifyRequestParamValidationService.validateParametersNotNull(albumId);
            this.albumId = albumId;
        }

        @Override
        public GetAlbum build()
        {
            ISpotifyRequestBuilder spotifyRequestBuilder = SpotifyClientComponentsFactory.spotifyRequestBuilder(REQUEST_URI_STRING);
            spotifyRequestBuilder.pathParam(PATH_PARAM, albumId);
            addOptionalQueryParams(spotifyRequestBuilder);

            return new GetAlbum(spotifyRequestBuilder.GET());
        }

        private void addOptionalQueryParams(ISpotifyRequestBuilder requestUriBuilder)
        {
            if(market != null)
            {
                requestUriBuilder.queryParam(MARKET_QUERY_PARAM, market);
            }
        }

        public Builder market(String market)
        {
            this.market = market;
            return this;
        }
    }
}
