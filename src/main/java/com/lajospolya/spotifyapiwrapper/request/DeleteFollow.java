package com.lajospolya.spotifyapiwrapper.request;

import com.lajospolya.spotifyapiwrapper.body.IdsContainer;
import com.lajospolya.spotifyapiwrapper.enumeration.FollowType;

import java.util.List;

/**
 * @author Lajos Polya
 *
 * Represents the endpoint at DELETE https://api.spotify.com/v1/me/following as descrbibed at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
public class DeleteFollow extends AbstractSpotifyRequest<Void>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "me/following";

    private DeleteFollow(ISpotifyRequestBuilder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder<DeleteFollow>
    {
        private FollowType type;
        private List<String> ids;

        public Builder(FollowType type, List<String> ids) throws IllegalArgumentException
        {
            spotifyRequestParamValidationService.validateParametersNotNull(type, ids);
            spotifyRequestParamValidationService.validateIds50(ids);
            this.type = type;
            this.ids = ids;
        }

        @Override
        public DeleteFollow build()
        {
            ISpotifyRequestBuilder spotifyRequestBuilder = SpotifyClientComponentsFactory.spotifyRequestBuilder(REQUEST_URI_STRING);
            spotifyRequestBuilder.queryParam(TYPE_QUERY_PARAM, type.getName());
            spotifyRequestBuilder.contentType(APPLICATION_JSON_CONTENT_TYPE_HEADER_VALUE);

            return new DeleteFollow(
                    spotifyRequestBuilder.DELETEWithJsonBody(
                            new IdsContainer(ids)));
        }
    }
}
