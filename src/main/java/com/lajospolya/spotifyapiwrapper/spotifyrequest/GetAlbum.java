package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import com.lajospolya.spotifyapiwrapper.response.Album;

import java.net.http.HttpRequest;

public final class GetAlbum extends AbstractSpotifyRequest<Album>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "albums/{id}";

    private GetAlbum(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder
    {
        private String albumId;
        private String market;

        public Builder(String albumId) throws IllegalArgumentException
        {
            validateParametersNotNull(albumId);
            this.albumId = albumId;
        }

        public GetAlbum build()
        {
            SpotifyRequestBuilder spotifyRequestBuilder = new SpotifyRequestBuilder(REQUEST_URI_STRING, albumId);
            addOptionalQueryParams(spotifyRequestBuilder);

            return new GetAlbum(spotifyRequestBuilder.createGetRequests());
        }

        private void addOptionalQueryParams(SpotifyRequestBuilder requestUriBuilder)
        {
            if(this.market != null)
            {
                requestUriBuilder.queryParam(MARKET_QUERY_PARAM, this.market);
            }
        }

        public Builder market(String market)
        {
            this.market = market;
            return this;
        }
    }
}
