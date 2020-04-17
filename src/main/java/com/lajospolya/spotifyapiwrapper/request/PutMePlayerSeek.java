package com.lajospolya.spotifyapiwrapper.request;

/**
 * @author Lajos Polya
 *
 * Represents the endpoint at PUT https://api.spotify.com/v1/me/player/seek as descrbibed at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
public class PutMePlayerSeek extends AbstractSpotifyRequest<Void>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "me/player/seek";

    private PutMePlayerSeek(ISpotifyRequestBuilder requestBuilder)
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
            ISpotifyRequestBuilder spotifyRequestBuilder = SpotifyClientComponentsFactory.spotifyRequestBuilder(REQUEST_URI_STRING);
            spotifyRequestBuilder.queryParam(POSITION_MS_QUERY_PARAM, positionMs);

            addOptionalQueryParams(spotifyRequestBuilder);

            return new PutMePlayerSeek(spotifyRequestBuilder.PUT());
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
