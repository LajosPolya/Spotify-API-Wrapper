package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import com.lajospolya.spotifyapiwrapper.response.Track;

import java.net.http.HttpRequest;

public final class GetTrack extends AbstractSpotifyRequest<Track>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "tracks/{id}";

    private GetTrack(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder
    {
        private String trackId;
        private String market;

        public Builder(String trackId)
        {
            validateParametersNotNull(trackId);
            this.trackId = trackId;
        }

        public GetTrack build()
        {
            SpotifyRequestBuilder spotifyRequestBuilder = new SpotifyRequestBuilder(REQUEST_URI_STRING, trackId);
            addOptionalQueryParams(spotifyRequestBuilder);

            return new GetTrack(spotifyRequestBuilder.createGetRequests());
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
