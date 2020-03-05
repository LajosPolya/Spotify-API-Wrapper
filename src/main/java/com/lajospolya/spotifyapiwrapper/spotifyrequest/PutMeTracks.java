package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import com.lajospolya.spotifyapiwrapper.body.IdsContainer;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.http.HttpRequest;
import java.util.List;

public class PutMeTracks extends AbstractSpotifyRequest<Void>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "me/tracks";

    private PutMeTracks(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder
    {
        private List<String> ids;

        public Builder(List<String> ids)
        {
            validateParametersNotNull(ids);
            spotifyRequestParamValidationService.validateFollowIds(ids);
            this.ids = ids;
        }

        public PutMeTracks build()
        {
            UriComponentsBuilder requestUriBuilder =  UriComponentsBuilder.fromUriString(REQUEST_URI_STRING);

            HttpRequest.BodyPublisher bodyPublisher = getBodyPublisher();

            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(requestUriBuilder.build().toUri())
                    .header(CONTENT_TYPE_HEADER, APPLICATION_JSON_CONTENT_TYPE_HEADER_VALUE)
                    .PUT(bodyPublisher);
            return new PutMeTracks(requestBuilder);
        }

        private HttpRequest.BodyPublisher getBodyPublisher()
        {
            return HttpRequest.BodyPublishers.ofString(gson.toJson(new IdsContainer(this.ids)));
        }
    }
}
