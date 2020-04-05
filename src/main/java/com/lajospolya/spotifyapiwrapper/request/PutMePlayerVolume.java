package com.lajospolya.spotifyapiwrapper.request;

/**
 * @author Lajos Polya
 *
 * Represents the endpoint at PUT https://api.spotify.com/v1/me/player/volume as descrbibed at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
public class PutMePlayerVolume extends AbstractSpotifyRequest<Void>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "me/player/volume";

    private PutMePlayerVolume(SpotifyRequestBuilder requestBuilder)
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

            return new PutMePlayerVolume(spotifyRequestBuilder.PUT());
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
