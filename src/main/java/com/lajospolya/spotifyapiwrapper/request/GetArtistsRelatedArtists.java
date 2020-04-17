package com.lajospolya.spotifyapiwrapper.request;

import com.lajospolya.spotifyapiwrapper.response.Artists;

/**
 * @author Lajos Polya
 *
 * Represents the endpoint at GET https://api.spotify.com/v1/artists/{id}/related-artists as descrbibed at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
public class GetArtistsRelatedArtists extends AbstractSpotifyRequest<Artists>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "artists/{id}/related-artists";

    private GetArtistsRelatedArtists(ISpotifyRequestBuilder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder<GetArtistsRelatedArtists>
    {
        private String artistId;

        public Builder(String artistId) throws IllegalArgumentException
        {
            spotifyRequestParamValidationService.validateParametersNotNull(artistId);
            this.artistId = artistId;
        }

        @Override
        public GetArtistsRelatedArtists build()
        {
            ISpotifyRequestBuilder spotifyRequestBuilder = SpotifyClientComponentsFactory.spotifyRequestBuilder(REQUEST_URI_STRING, artistId);

            return new GetArtistsRelatedArtists(spotifyRequestBuilder.GET());
        }
    }
}
