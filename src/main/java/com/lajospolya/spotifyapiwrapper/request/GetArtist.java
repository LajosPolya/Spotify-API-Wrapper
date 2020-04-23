package com.lajospolya.spotifyapiwrapper.request;

import com.lajospolya.spotifyapiwrapper.component.ISpotifyRequestBuilder;
import com.lajospolya.spotifyapiwrapper.component.SpotifyClientComponentsFactory;
import com.lajospolya.spotifyapiwrapper.response.Artist;

/**
 * @author Lajos Polya
 *
 * Represents the endpoint at GET https://api.spotify.com/v1/artists/{id} as descrbibed at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
public class GetArtist extends AbstractSpotifyRequest<Artist>
{
    private static final String PATH_PARAM = "{id}";
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "artists/{id}";

    private GetArtist(ISpotifyRequestBuilder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder<GetArtist>
    {
        private final String artistId;

        public Builder(String artistId) throws IllegalArgumentException
        {
            spotifyRequestParamValidationService.validateParametersNotNull(artistId);
            this.artistId = artistId;
        }

        @Override
        public GetArtist build()
        {
            ISpotifyRequestBuilder spotifyRequestBuilder = SpotifyClientComponentsFactory.spotifyRequestBuilder(REQUEST_URI_STRING)
            .pathParam(PATH_PARAM, artistId);

            return new GetArtist(spotifyRequestBuilder.GET());
        }
    }
}
