package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import com.lajospolya.spotifyapiwrapper.body.IdsContainer;

import java.net.http.HttpRequest;
import java.util.List;

public class DeleteMeAlbums extends AbstractSpotifyRequest<Void>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "me/albums";

    private DeleteMeAlbums(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder
    {
        private List<String> albumIds;

        public Builder(List<String> albumIds)
        {
            validateParametersNotNull(albumIds);
            spotifyRequestParamValidationService.validateFollowIds(albumIds);
            this.albumIds = albumIds;
        }

        public DeleteMeAlbums build()
        {
            SpotifyRequestBuilder spotifyRequestBuilder = new SpotifyRequestBuilder(REQUEST_URI_STRING);
            spotifyRequestBuilder.header(CONTENT_TYPE_HEADER, APPLICATION_JSON_CONTENT_TYPE_HEADER_VALUE);

            return new DeleteMeAlbums(
                    spotifyRequestBuilder.createDeleteRequestWithObjectJsonBody(
                            new IdsContainer(this.albumIds)));
        }
    }
}
