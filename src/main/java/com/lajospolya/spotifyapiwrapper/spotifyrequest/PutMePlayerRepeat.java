package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import com.lajospolya.spotifyapiwrapper.enumeration.RepeatState;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.http.HttpRequest;

public class PutMePlayerRepeat extends AbstractSpotifyRequest<Void>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "me/player/repeat";

    private PutMePlayerRepeat(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder
    {
        private RepeatState state;
        private String deviceId;

        public Builder(RepeatState state) throws IllegalArgumentException
        {
            validateParametersNotNull(state);
            this.state = state;
        }

        public PutMePlayerRepeat build()
        {
            UriComponentsBuilder requestUriBuilder =  UriComponentsBuilder.fromUriString(REQUEST_URI_STRING);
            requestUriBuilder.queryParam(STATE_QUERY_PARAM, state.getState());
            addOptionalQueryParams(requestUriBuilder);

            HttpRequest.BodyPublisher bodyPublisher = getBodyPublisher();

            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(requestUriBuilder.build().toUri())
                    .PUT(bodyPublisher);
            return new PutMePlayerRepeat(requestBuilder);
        }

        private void addOptionalQueryParams(UriComponentsBuilder requestUriBuilder)
        {
            if(this.deviceId != null)
            {
                requestUriBuilder.queryParam(DEVICE_ID_QUERY_PARAM, this.deviceId);
            }
        }

        private HttpRequest.BodyPublisher getBodyPublisher()
        {
            return HttpRequest.BodyPublishers.noBody();
        }

        public Builder deviceId(String deviceId)
        {
            this.deviceId = deviceId;
            return this;
        }
    }
}
