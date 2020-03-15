package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import com.lajospolya.spotifyapiwrapper.response.Artists;

import java.net.http.HttpRequest;

public final class GetArtistsRelatedArtists extends AbstractSpotifyRequest<Artists>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "artists/{id}/related-artists";

    private GetArtistsRelatedArtists(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder
    {
        private String artistId;

        public Builder(String artistId) throws IllegalArgumentException
        {
            validateParametersNotNull(artistId);
            this.artistId = artistId;
        }

        public GetArtistsRelatedArtists build()
        {
            SpotifyRequestBuilder spotifyRequestBuilder = new SpotifyRequestBuilder(REQUEST_URI_STRING, artistId);

            return new GetArtistsRelatedArtists(spotifyRequestBuilder.createGetRequests());
        }
    }
}
