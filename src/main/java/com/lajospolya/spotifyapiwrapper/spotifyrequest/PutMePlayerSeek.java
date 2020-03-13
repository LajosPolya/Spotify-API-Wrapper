package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import java.net.http.HttpRequest;

public class PutMePlayerSeek extends AbstractSpotifyRequest<Void>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "me/player/seek";

    private PutMePlayerSeek(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder
    {
        private Integer positionMs;
        private String deviceId;

        public Builder(Integer positionMs) throws IllegalArgumentException
        {
            validateParametersNotNull(positionMs);
            this.positionMs = positionMs;
        }

        public PutMePlayerSeek build()
        {
            SpotifyRequestBuilder spotifyRequestBuilder = new SpotifyRequestBuilder(REQUEST_URI_STRING);
            spotifyRequestBuilder.addQueryParam(POSITION_MS_QUERY_PARAM, positionMs);

            addOptionalQueryParams(spotifyRequestBuilder);

            return new PutMePlayerSeek(spotifyRequestBuilder.createPutRequest());
        }

        private void addOptionalQueryParams(SpotifyRequestBuilder requestUriBuilder)
        {
            if(this.deviceId != null)
            {
                requestUriBuilder.addQueryParam(DEVICE_ID_QUERY_PARAM, this.deviceId);
            }
        }

        public Builder deviceId(String deviceId)
        {
            this.deviceId = deviceId;
            return this;
        }
    }
}
