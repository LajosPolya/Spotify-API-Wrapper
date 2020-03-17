package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import com.lajospolya.spotifyapiwrapper.response.Albums;

import java.net.http.HttpRequest;
import java.util.List;

public final class GetAlbums extends AbstractSpotifyRequest<Albums>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "albums";

    private GetAlbums(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder<GetAlbums>
    {
        private List<String> albumIds;
        private String market;

        public Builder(List<String> albumIds) throws IllegalArgumentException
        {
            spotifyRequestParamValidationService.validateParametersNotNull(albumIds);
            this.albumIds = albumIds;
        }

        @Override
        public GetAlbums build()
        {
            SpotifyRequestBuilder spotifyRequestBuilder = new SpotifyRequestBuilder(REQUEST_URI_STRING);
            spotifyRequestBuilder.queryParam(IDS_QUERY_PARAM, albumIds);
            addOptionalQueryParams(spotifyRequestBuilder);

            return new GetAlbums(spotifyRequestBuilder.createGetRequests());
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
