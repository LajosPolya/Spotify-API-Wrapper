package com.lajospolya.spotifyapiwrapper.request;

import com.lajospolya.spotifyapiwrapper.response.Show;

import java.net.http.HttpRequest;

public class GetShow extends AbstractSpotifyRequest<Show>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "shows/{id}";

    private GetShow(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder<GetShow>
    {
        private String showId;

        private String market;

        public Builder(String showId)
        {
            spotifyRequestParamValidationService.validateParametersNotNull(showId);
            this.showId = showId;
        }

        @Override
        public GetShow build()
        {
            SpotifyRequestBuilder spotifyRequestBuilder = new SpotifyRequestBuilder(REQUEST_URI_STRING, showId);
            addOptionalQueryParams(spotifyRequestBuilder);
            return new GetShow(spotifyRequestBuilder.createGetRequests());
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
