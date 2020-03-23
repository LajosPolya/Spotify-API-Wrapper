package com.lajospolya.spotifyapiwrapper.request;

import com.lajospolya.spotifyapiwrapper.response.Album;

import java.net.http.HttpRequest;

public final class GetAlbum extends AbstractSpotifyRequest<Album>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "albums/{id}";

    private GetAlbum(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder<GetAlbum>
    {
        private String albumId;
        private String market;

        public Builder(String albumId) throws IllegalArgumentException
        {
            spotifyRequestParamValidationService.validateParametersNotNull(albumId);
            this.albumId = albumId;
        }

        @Override
        public GetAlbum build()
        {
            SpotifyRequestBuilder spotifyRequestBuilder = new SpotifyRequestBuilder(REQUEST_URI_STRING, albumId);
            addOptionalQueryParams(spotifyRequestBuilder);

            return new GetAlbum(spotifyRequestBuilder.createGetRequests());
        }

        private void addOptionalQueryParams(SpotifyRequestBuilder requestUriBuilder)
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
