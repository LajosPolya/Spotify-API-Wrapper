package com.lajospolya.spotifyapiwrapper.request;

import com.lajospolya.spotifyapiwrapper.enumeration.RepeatState;

import java.net.http.HttpRequest;

/**
 * @author Lajos Polya
 *
 * Represents the endpoint at PUT https://api.spotify.com/v1/me/player/repeat as descrbibed at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
public class PutMePlayerRepeat extends AbstractSpotifyRequest<Void>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "me/player/repeat";

    private PutMePlayerRepeat(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder<PutMePlayerRepeat>
    {
        private RepeatState state;
        private String deviceId;

        public Builder(RepeatState state) throws IllegalArgumentException
        {
            spotifyRequestParamValidationService.validateParametersNotNull(state);
            this.state = state;
        }

        @Override
        public PutMePlayerRepeat build()
        {
            SpotifyRequestBuilder spotifyRequestBuilder = new SpotifyRequestBuilder(REQUEST_URI_STRING);
            spotifyRequestBuilder.queryParam(STATE_QUERY_PARAM, state.getState());
            addOptionalQueryParams(spotifyRequestBuilder);

            return new PutMePlayerRepeat(spotifyRequestBuilder.createPutRequest());
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
