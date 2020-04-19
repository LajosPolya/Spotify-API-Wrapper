package com.lajospolya.spotifyapiwrapper.request;

import com.lajospolya.spotifyapiwrapper.internal.ISpotifyRequestBuilder;
import com.lajospolya.spotifyapiwrapper.internal.SpotifyClientComponentsFactory;
import com.lajospolya.spotifyapiwrapper.response.Artists;

import java.util.List;

/**
 * @author Lajos Polya
 *
 * Represents the endpoint at GET https://api.spotify.com/v1/artists as descrbibed at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
public class GetArtists extends AbstractSpotifyRequest<Artists>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "artists";

    private GetArtists(ISpotifyRequestBuilder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder<GetArtists>
    {
        private List<String> artistIds;

        public Builder(List<String> artistIds) throws IllegalArgumentException
        {
            spotifyRequestParamValidationService.validateParametersNotNull(artistIds);
            this.artistIds = artistIds;
        }

        @Override
        public GetArtists build()
        {
            ISpotifyRequestBuilder spotifyRequestBuilder = SpotifyClientComponentsFactory.spotifyRequestBuilder(REQUEST_URI_STRING);
            spotifyRequestBuilder.queryParam(IDS_QUERY_PARAM, artistIds);

            return new GetArtists(spotifyRequestBuilder.GET());
        }
    }
}
