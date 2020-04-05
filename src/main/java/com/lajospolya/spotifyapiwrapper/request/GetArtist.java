package com.lajospolya.spotifyapiwrapper.request;

import com.lajospolya.spotifyapiwrapper.response.Artist;

/**
 * @author Lajos Polya
 *
 * Represents the endpoint at GET https://api.spotify.com/v1/artists/{id} as descrbibed at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
public class GetArtist extends AbstractSpotifyRequest<Artist>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "artists/{id}";

    private GetArtist(SpotifyRequestBuilder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder<GetArtist>
    {
        private String artistId;

        public Builder(String artistId) throws IllegalArgumentException
        {
            spotifyRequestParamValidationService.validateParametersNotNull(artistId);
            this.artistId = artistId;
        }

        @Override
        public GetArtist build()
        {
            SpotifyRequestBuilder spotifyRequestBuilder = new SpotifyRequestBuilder(REQUEST_URI_STRING, artistId);

            return new GetArtist(spotifyRequestBuilder.GET());
        }
    }
}
