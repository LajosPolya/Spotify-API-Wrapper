package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import java.net.http.HttpRequest;

public class PostMePlayerQueue extends AbstractSpotifyRequest<Void>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "me/player/queue";

    private PostMePlayerQueue(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder
    {
        private String uri;
        private String deviceId;

        public Builder(String uri) throws IllegalArgumentException
        {
            spotifyRequestParamValidationService.validateParametersNotNull(uri);
            this.uri = uri;
        }

        public PostMePlayerQueue build()
        {
            SpotifyRequestBuilder spotifyRequestBuilder = new SpotifyRequestBuilder(REQUEST_URI_STRING);
            spotifyRequestBuilder.queryParam(URI_QUERY_PARAM, uri);
            addOptionalQueryParams(spotifyRequestBuilder);

            return new PostMePlayerQueue(spotifyRequestBuilder.createPostRequests());
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
