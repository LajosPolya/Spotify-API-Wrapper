package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import com.lajospolya.spotifyapiwrapper.body.IdsContainer;

import java.net.http.HttpRequest;
import java.util.List;

public class DeleteMeTracks extends AbstractSpotifyRequest<Void>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "me/tracks";

    private DeleteMeTracks(HttpRequest.Builder requestBuilder)
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

        public DeleteMeTracks build()
        {
            SpotifyRequestBuilder spotifyRequestBuilder = new SpotifyRequestBuilder(REQUEST_URI_STRING);
            spotifyRequestBuilder.header(CONTENT_TYPE_HEADER, APPLICATION_JSON_CONTENT_TYPE_HEADER_VALUE);

            return new DeleteMeTracks(
                    spotifyRequestBuilder.createDeleteRequestWithObjectJsonBody(
                            new IdsContainer(this.ids)));
        }
    }
}
