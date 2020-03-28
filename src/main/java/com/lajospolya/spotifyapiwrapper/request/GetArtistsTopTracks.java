package com.lajospolya.spotifyapiwrapper.request;

import com.lajospolya.spotifyapiwrapper.response.ArtistsTopTracks;

import java.net.http.HttpRequest;

/**
 * @author Lajos Polya
 *
 * Represents the endpoint at GET https://api.spotify.com/v1/artists/{id}/top-tracks as descrbibed at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
public class GetArtistsTopTracks extends AbstractSpotifyRequest<ArtistsTopTracks>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "artists/{id}/top-tracks";

    private GetArtistsTopTracks(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder<GetArtistsTopTracks>
    {
        private String artistId;
        private String market;

        public Builder(String artistId, String market) throws IllegalArgumentException
        {
            spotifyRequestParamValidationService.validateParametersNotNull(artistId, market);
            this.artistId = artistId;
            this.market = market;
        }

        @Override
        public GetArtistsTopTracks build()
        {
            SpotifyRequestBuilder spotifyRequestBuilder = new SpotifyRequestBuilder(REQUEST_URI_STRING, artistId);
            spotifyRequestBuilder.queryParam(MARKET_QUERY_PARAM, market);

            return new GetArtistsTopTracks(spotifyRequestBuilder.createGetRequests());
        }
    }
}