package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import com.lajospolya.spotifyapiwrapper.enumeration.RepeatState;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.http.HttpRequest;

public class PutMePlayerVolume extends AbstractSpotifyRequest<Void>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "me/player/volume";

    private PutMePlayerVolume(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder
    {
        private RepeatState state;
        private Integer volumePercent;
        private String deviceId;

        public Builder(RepeatState state, Integer volumePercent) throws IllegalArgumentException
        {
            validateParametersNotNull(state, volumePercent);
            spotifyRequestParamValidationService.validateVolume(volumePercent);
            this.state = state;
            this.volumePercent = volumePercent;
        }

        public PutMePlayerVolume build()
        {
            UriComponentsBuilder requestUriBuilder =  UriComponentsBuilder.fromUriString(REQUEST_URI_STRING);
            requestUriBuilder.queryParam(STATE_QUERY_PARAM, state.getState());
            requestUriBuilder.queryParam(VOLUME_PERCENT_QUERY_PARAM, volumePercent);

            addOptionalQueryParams(requestUriBuilder);

            HttpRequest.BodyPublisher bodyPublisher = getBodyPublisher();

            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(requestUriBuilder.build().toUri())
                    .PUT(bodyPublisher);
            return new PutMePlayerVolume(requestBuilder);
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
