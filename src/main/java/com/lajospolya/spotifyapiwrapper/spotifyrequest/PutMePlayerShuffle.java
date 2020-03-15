package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import java.net.http.HttpRequest;

public class PutMePlayerShuffle extends AbstractSpotifyRequest<Void>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "me/player/shuffle";

    private PutMePlayerShuffle(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder
    {
        private Boolean state;
        private String deviceId;

        public Builder(Boolean state) throws IllegalArgumentException
        {
            validateParametersNotNull(state);
            this.state = state;
        }

        public PutMePlayerShuffle build()
        {
            SpotifyRequestBuilder spotifyRequestBuilder = new SpotifyRequestBuilder(REQUEST_URI_STRING);
            spotifyRequestBuilder.queryParam(STATE_QUERY_PARAM, state);

            addOptionalQueryParams(spotifyRequestBuilder);

            return new PutMePlayerShuffle(spotifyRequestBuilder.createPutRequest());
        }

        private void addOptionalQueryParams(SpotifyRequestBuilder requestUriBuilder)
        {
            if(this.deviceId != null)
            {
                requestUriBuilder.queryParam(DEVICE_ID_QUERY_PARAM, this.deviceId);
            }
        }

        public Builder deviceId(String deviceId)
        {
            this.deviceId = deviceId;
            return this;
        }
    }
}
