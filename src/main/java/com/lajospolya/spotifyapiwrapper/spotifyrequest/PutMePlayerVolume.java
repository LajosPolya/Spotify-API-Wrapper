package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import java.net.http.HttpRequest;

public final class PutMePlayerVolume extends AbstractSpotifyRequest<Void>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "me/player/volume";

    private PutMePlayerVolume(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder<PutMePlayerVolume>
    {
        private Integer volumePercent;
        private String deviceId;

        public Builder(Integer volumePercent) throws IllegalArgumentException
        {
            spotifyRequestParamValidationService.validateParametersNotNull(volumePercent);
            spotifyRequestParamValidationService.validateVolume(volumePercent);
            this.volumePercent = volumePercent;
        }

        @Override
        public PutMePlayerVolume build()
        {
            SpotifyRequestBuilder spotifyRequestBuilder = new SpotifyRequestBuilder(REQUEST_URI_STRING);
            spotifyRequestBuilder.queryParam(VOLUME_PERCENT_QUERY_PARAM, volumePercent);
            addOptionalQueryParams(spotifyRequestBuilder);

            return new PutMePlayerVolume(spotifyRequestBuilder.createPutRequest());
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
