package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import org.springframework.web.util.UriComponentsBuilder;

import java.net.http.HttpRequest;

public class PutMePlayerSeek extends AbstractSpotifyRequest<Void>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "me/player/seek";

    private PutMePlayerSeek(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder
    {
        private Integer positionMs;
        private String deviceId;

        public Builder(Integer positionMs) throws IllegalArgumentException
        {
            validateParametersNotNull(positionMs);
            this.positionMs = positionMs;
        }

        public PutMePlayerSeek build()
        {
            UriComponentsBuilder requestUriBuilder =  UriComponentsBuilder.fromUriString(REQUEST_URI_STRING);
            requestUriBuilder.queryParam(POSITION_MS_QUERY_PARAM, positionMs);

            addOptionalQueryParams(requestUriBuilder);

            HttpRequest.BodyPublisher bodyPublisher = getBodyPublisher();

            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(requestUriBuilder.build().toUri())
                    .PUT(bodyPublisher);
            return new PutMePlayerSeek(requestBuilder);
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
