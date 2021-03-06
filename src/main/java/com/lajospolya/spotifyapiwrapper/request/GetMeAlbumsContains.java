package com.lajospolya.spotifyapiwrapper.request;

import com.lajospolya.spotifyapiwrapper.component.ISpotifyRequestBuilder;
import com.lajospolya.spotifyapiwrapper.component.SpotifyClientComponentsFactory;

import java.util.List;

/**
 * @author Lajos Polya
 *
 * Represents the endpoint at GET https://api.spotify.com/v1/me/albums/contains as descrbibed at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
public class GetMeAlbumsContains extends AbstractSpotifyRequest<List<Boolean>>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "me/albums/contains";

    private GetMeAlbumsContains(ISpotifyRequestBuilder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder<GetMeAlbumsContains>
    {
        private final List<String> albumIds;

        public Builder(List<String> albumIds) throws IllegalArgumentException
        {
            spotifyRequestParamValidationService.validateParametersNotNull(albumIds);
            spotifyRequestParamValidationService.validateIds50(albumIds);
            this.albumIds = albumIds;
        }

        @Override
        public GetMeAlbumsContains build()
        {
            ISpotifyRequestBuilder spotifyRequestBuilder = SpotifyClientComponentsFactory.spotifyRequestBuilder(REQUEST_URI_STRING)
            .queryParam(IDS_QUERY_PARAM, albumIds);

            return new GetMeAlbumsContains(spotifyRequestBuilder.GET());
        }
    }
}
