package com.lajospolya.spotifyapiwrapper.request;

import com.lajospolya.spotifyapiwrapper.body.IdsContainer;

import java.util.List;

/**
 * @author Lajos Polya
 *
 * Represents the endpoint at PUT https://api.spotify.com/v1/me/tracks as descrbibed at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
public class PutMeTracks extends AbstractSpotifyRequest<Void>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "me/tracks";

    private PutMeTracks(ISpotifyRequestBuilder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder<PutMeTracks>
    {
        private List<String> trackIds;

        public Builder(List<String> trackIds) throws IllegalArgumentException
        {
            spotifyRequestParamValidationService.validateParametersNotNull(trackIds);
            spotifyRequestParamValidationService.validateIds50(trackIds);
            this.trackIds = trackIds;
        }

        @Override
        public PutMeTracks build()
        {
            ISpotifyRequestBuilder spotifyRequestBuilder = SpotifyClientComponentsFactory.spotifyRequestBuilder(REQUEST_URI_STRING);
            spotifyRequestBuilder.contentType(APPLICATION_JSON_CONTENT_TYPE_HEADER_VALUE);

            return new PutMeTracks(
                    spotifyRequestBuilder.PUTWithJsonBody(
                            new IdsContainer(trackIds)));
        }
    }
}
