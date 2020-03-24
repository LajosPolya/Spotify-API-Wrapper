package com.lajospolya.spotifyapiwrapper.request;

import com.lajospolya.spotifyapiwrapper.body.ResumePlayback;

import java.net.http.HttpRequest;
import java.util.List;

public class PutMePlayerPlay extends AbstractSpotifyRequest<Void>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "me/player/play";

    private PutMePlayerPlay(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder<PutMePlayerPlay>
    {
        private String deviceId;

        private String contextUri;
        private List<String> uris;
        private Integer offset;
        private Integer positionMs;

        public Builder() { }

        @Override
        public PutMePlayerPlay build()
        {
            SpotifyRequestBuilder spotifyRequestBuilder = new SpotifyRequestBuilder(REQUEST_URI_STRING);
            addOptionalQueryParams(spotifyRequestBuilder);

            return new PutMePlayerPlay(createRequest(spotifyRequestBuilder));
        }

        private void addOptionalQueryParams(SpotifyRequestBuilder requestUriBuilder)
        {
            if(deviceId != null)
            {
                requestUriBuilder.queryParam(DEVICE_ID_QUERY_PARAM, deviceId);
            }
        }

        private HttpRequest.Builder createRequest(SpotifyRequestBuilder spotifyRequestBuilder)
        {
            if(contextUri != null || uris != null || offset != null || positionMs != null)
            {
                return spotifyRequestBuilder.createPutRequestWithObjectJsonBody(
                        new ResumePlayback(contextUri, uris, offset, positionMs));
            }
            return spotifyRequestBuilder.createPutRequest();
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
