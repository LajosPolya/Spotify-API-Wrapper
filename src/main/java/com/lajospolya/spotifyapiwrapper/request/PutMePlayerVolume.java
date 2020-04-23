package com.lajospolya.spotifyapiwrapper.request;

import com.lajospolya.spotifyapiwrapper.component.ISpotifyRequestBuilder;
import com.lajospolya.spotifyapiwrapper.component.SpotifyClientComponentsFactory;

/**
 * @author Lajos Polya
 *
 * Represents the endpoint at PUT https://api.spotify.com/v1/me/player/volume as descrbibed at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
public class PutMePlayerVolume extends AbstractSpotifyRequest<Void>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "me/player/volume";

    private PutMePlayerVolume(ISpotifyRequestBuilder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder<PutMePlayerVolume>
    {
        private final Integer volumePercent;
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
            ISpotifyRequestBuilder spotifyRequestBuilder = SpotifyClientComponentsFactory.spotifyRequestBuilder(REQUEST_URI_STRING)
            .queryParam(VOLUME_PERCENT_QUERY_PARAM, volumePercent);
            addOptionalQueryParams(spotifyRequestBuilder);

            return new PutMePlayerVolume(spotifyRequestBuilder.PUT());
        }

        private void addOptionalQueryParams(ISpotifyRequestBuilder requestUriBuilder)
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
