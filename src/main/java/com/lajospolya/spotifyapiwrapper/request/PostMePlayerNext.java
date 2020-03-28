package com.lajospolya.spotifyapiwrapper.request;

import java.net.http.HttpRequest;

/**
 * @author Lajos Polya
 *
 * Represents the endpoint at POST https://api.spotify.com/v1/me/player/next as descrbibed at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
public class PostMePlayerNext extends AbstractSpotifyRequest<Void>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "me/player/next";

    private PostMePlayerNext(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder<PostMePlayerNext>
    {
        private String deviceId;


        public Builder() { }

        @Override
        public PostMePlayerNext build()
        {
            SpotifyRequestBuilder spotifyRequestBuilder = new SpotifyRequestBuilder(REQUEST_URI_STRING);
            addOptionalQueryParams(spotifyRequestBuilder);

            return new PostMePlayerNext(spotifyRequestBuilder.createPostRequests());
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
