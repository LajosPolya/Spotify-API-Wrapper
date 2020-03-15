package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import com.lajospolya.spotifyapiwrapper.body.IdsContainer;

import java.net.http.HttpRequest;
import java.util.List;

public class PutMeAlbums extends AbstractSpotifyRequest<Void>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "me/albums";

    private PutMeAlbums(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder
    {
        private List<String> albumIds;

        public Builder(List<String> albumIds) throws IllegalArgumentException
        {
            spotifyRequestParamValidationService.validateParametersNotNull(albumIds);
            spotifyRequestParamValidationService.validateFollowIds(albumIds);
            this.albumIds = albumIds;
        }

        public PutMeAlbums build()
        {
            SpotifyRequestBuilder spotifyRequestBuilder = new SpotifyRequestBuilder(REQUEST_URI_STRING);
            spotifyRequestBuilder.header(CONTENT_TYPE_HEADER, APPLICATION_JSON_CONTENT_TYPE_HEADER_VALUE);

            return new PutMeAlbums(
                    spotifyRequestBuilder.createPutRequestWithObjectJsonBody(
                            new IdsContainer(this.albumIds)));
        }
    }
}
