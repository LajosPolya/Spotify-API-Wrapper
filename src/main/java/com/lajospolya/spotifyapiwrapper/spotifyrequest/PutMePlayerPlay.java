package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import com.lajospolya.spotifyapiwrapper.body.ResumePlayback;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.http.HttpRequest;
import java.util.List;

public class PutMePlayerPlay extends AbstractSpotifyRequest<Void>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "me/player/play";

    private PutMePlayerPlay(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder
    {
        private String deviceId;

        private String contextUri;
        private List<String> uris;
        private Integer offset;
        private Integer positionMs;

        public Builder() { }

        public PutMePlayerPlay build()
        {
            UriComponentsBuilder requestUriBuilder =  UriComponentsBuilder.fromUriString(REQUEST_URI_STRING);
            addOptionalQueryParams(requestUriBuilder);

            HttpRequest.BodyPublisher bodyPublisher = getBodyPublisher();

            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(requestUriBuilder.build().toUri())
                    .PUT(bodyPublisher);
            return new PutMePlayerPlay(requestBuilder);
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
            if(contextUri != null || uris != null || offset != null || positionMs != null)
            {
                HttpRequest.BodyPublishers.ofString(gson.toJson(new ResumePlayback(contextUri, uris, offset, positionMs)));
            }
            return HttpRequest.BodyPublishers.noBody();
        }

        public Builder contextUri(String contextUri)
        {
            this.contextUri = contextUri;
            return this;
        }

        public Builder uris(List<String> uris)
        {
            this.uris = uris;
            return this;
        }

        public Builder offset(Integer offset)
        {
            this.offset = offset;
            return this;
        }

        public Builder positionMs(Integer positionMs)
        {
            this.positionMs = positionMs;
            return this;
        }

        public Builder deviceId(String deviceId)
        {
            this.deviceId = deviceId;
            return this;
        }
    }
}
