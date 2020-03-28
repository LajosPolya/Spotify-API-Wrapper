package com.lajospolya.spotifyapiwrapper.request;

import java.net.http.HttpRequest;

/**
 * @author Lajos Polya
 *
 * Represents the endpoint at PUT https://api.spotify.com/v1/me/player/shuffle as descrbibed at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
public class PutMePlayerShuffle extends AbstractSpotifyRequest<Void>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "me/player/shuffle";

    private PutMePlayerShuffle(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder<PutMePlayerShuffle>
    {
        private Boolean state;
        private String deviceId;

        public Builder(Boolean state) throws IllegalArgumentException
        {
            spotifyRequestParamValidationService.validateParametersNotNull(state);
            this.state = state;
        }

        @Override
        public PutMePlayerShuffle build()
        {
            SpotifyRequestBuilder spotifyRequestBuilder = new SpotifyRequestBuilder(REQUEST_URI_STRING);
            spotifyRequestBuilder.queryParam(STATE_QUERY_PARAM, state);

            addOptionalQueryParams(spotifyRequestBuilder);

            return new PutMePlayerShuffle(spotifyRequestBuilder.createPutRequest());
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