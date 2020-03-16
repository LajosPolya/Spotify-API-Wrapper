package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import com.lajospolya.spotifyapiwrapper.enumeration.RepeatState;

import java.net.http.HttpRequest;

public final class PutMePlayerRepeat extends AbstractSpotifyRequest<Void>
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
