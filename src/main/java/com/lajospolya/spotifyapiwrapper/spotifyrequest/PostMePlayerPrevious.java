package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import java.net.http.HttpRequest;

public class PostMePlayerPrevious extends AbstractSpotifyRequest<Void>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "me/player/previous";

    private PostMePlayerPrevious(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder
    {
        private String deviceId;

        public Builder() { }

        public PostMePlayerPrevious build()
        {
            SpotifyRequestBuilder spotifyRequestBuilder = new SpotifyRequestBuilder(REQUEST_URI_STRING);
            addOptionalQueryParams(spotifyRequestBuilder);

            return new PostMePlayerPrevious(spotifyRequestBuilder.createPostRequests());
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
