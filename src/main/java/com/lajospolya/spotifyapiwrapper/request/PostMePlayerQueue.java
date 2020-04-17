package com.lajospolya.spotifyapiwrapper.request;

import com.lajospolya.spotifyapiwrapper.internal.SpotifyClientComponentsFactory;

/**
 * @author Lajos Polya
 *
 * Represents the endpoint at POST https://api.spotify.com/v1/me/player/queue as descrbibed at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
public class PostMePlayerQueue extends AbstractSpotifyRequest<Void>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "me/player/queue";

    private PostMePlayerQueue(ISpotifyRequestBuilder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder<PostMePlayerQueue>
    {
        private String uri;
        private String deviceId;

        public Builder(String uri) throws IllegalArgumentException
        {
            spotifyRequestParamValidationService.validateParametersNotNull(uri);
            this.uri = uri;
        }

        @Override
        public PostMePlayerQueue build()
        {
            ISpotifyRequestBuilder spotifyRequestBuilder = SpotifyClientComponentsFactory.spotifyRequestBuilder(REQUEST_URI_STRING);
            spotifyRequestBuilder.queryParam(URI_QUERY_PARAM, uri);
            addOptionalQueryParams(spotifyRequestBuilder);

            return new PostMePlayerQueue(spotifyRequestBuilder.POST());
        }

        private void addOptionalQueryParams(ISpotifyRequestBuilder requestUriBuilder)
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
