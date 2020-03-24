package com.lajospolya.spotifyapiwrapper.request;

import java.net.http.HttpRequest;

public class PutMePlayerSeek extends AbstractSpotifyRequest<Void>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "me/player/seek";

    private PutMePlayerSeek(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder<PutMePlayerSeek>
    {
        private Integer positionMs;
        private String deviceId;

        public Builder(Integer positionMs) throws IllegalArgumentException
        {
            spotifyRequestParamValidationService.validateParametersNotNull(positionMs);
            this.positionMs = positionMs;
        }

        @Override
        public PutMePlayerSeek build()
        {
            SpotifyRequestBuilder spotifyRequestBuilder = new SpotifyRequestBuilder(REQUEST_URI_STRING);
            spotifyRequestBuilder.queryParam(POSITION_MS_QUERY_PARAM, positionMs);

            addOptionalQueryParams(spotifyRequestBuilder);

            return new PutMePlayerSeek(spotifyRequestBuilder.createPutRequest());
        }

        private void addOptionalQueryParams(SpotifyRequestBuilder requestUriBuilder)
        {
            if(deviceId != null)
            {
                requestUriBuilder.queryParam(DEVICE_ID_QUERY_PARAM, deviceId);
            }
        }

        public Builder deviceId(String deviceId)
        {
            this.deviceId = deviceId;
            return this;
        }
    }
}
