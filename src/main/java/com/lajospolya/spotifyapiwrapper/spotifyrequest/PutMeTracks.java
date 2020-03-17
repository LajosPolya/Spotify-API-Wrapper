package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import com.lajospolya.spotifyapiwrapper.body.IdsContainer;

import java.net.http.HttpRequest;
import java.util.List;

public final class PutMeTracks extends AbstractSpotifyRequest<Void>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "me/tracks";

    private PutMeTracks(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder<PutMeTracks>
    {
        private List<String> trackIds;

        public Builder(List<String> trackIds) throws IllegalArgumentException
        {
            spotifyRequestParamValidationService.validateParametersNotNull(trackIds);
            spotifyRequestParamValidationService.validateFollowIds(trackIds);
            this.trackIds = trackIds;
        }

        @Override
        public PutMeTracks build()
        {
            SpotifyRequestBuilder spotifyRequestBuilder = new SpotifyRequestBuilder(REQUEST_URI_STRING);
            spotifyRequestBuilder.contentType(APPLICATION_JSON_CONTENT_TYPE_HEADER_VALUE);

            return new PutMeTracks(
                    spotifyRequestBuilder.createPutRequestWithObjectJsonBody(
                            new IdsContainer(trackIds)));
        }
    }
}
