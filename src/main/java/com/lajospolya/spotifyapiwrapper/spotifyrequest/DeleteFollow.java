package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import com.lajospolya.spotifyapiwrapper.body.IdsContainer;
import com.lajospolya.spotifyapiwrapper.enumeration.FollowType;

import java.net.http.HttpRequest;
import java.util.List;

public class DeleteFollow extends AbstractSpotifyRequest<Void>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "me/following";

    private DeleteFollow(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder
    {
        private FollowType type;
        private List<String> ids;

        public Builder(FollowType type, List<String> ids) throws IllegalArgumentException
        {
            spotifyRequestParamValidationService.validateParametersNotNull(type, ids);
            spotifyRequestParamValidationService.validateFollowIds(ids);
            this.type = type;
            this.ids = ids;
        }

        public DeleteFollow build()
        {
            SpotifyRequestBuilder spotifyRequestBuilder = new SpotifyRequestBuilder(REQUEST_URI_STRING);
            spotifyRequestBuilder.queryParam(TYPE_QUERY_PARAM, type.getName());
            spotifyRequestBuilder.header(CONTENT_TYPE_HEADER, APPLICATION_JSON_CONTENT_TYPE_HEADER_VALUE);

            return new DeleteFollow(
                    spotifyRequestBuilder.createDeleteRequestWithObjectJsonBody(
                            new IdsContainer(this.ids)));
        }
    }
}
