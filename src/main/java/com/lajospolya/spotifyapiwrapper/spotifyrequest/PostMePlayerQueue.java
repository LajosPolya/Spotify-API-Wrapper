package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import org.springframework.web.util.UriComponentsBuilder;

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
            validateParametersNotNull(uri);
            this.uri = uri;
        }

        public PostMePlayerQueue build()
        {
            UriComponentsBuilder requestUriBuilder =  UriComponentsBuilder.fromUriString(REQUEST_URI_STRING);
            requestUriBuilder.queryParam(URI_QUERY_PARAM, uri);


            HttpRequest.BodyPublisher bodyPublisher = getBodyPublisher();

            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(requestUriBuilder.build().toUri())
                    .POST(bodyPublisher);
            return new PostMePlayerQueue(requestBuilder);
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
