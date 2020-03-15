package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import com.lajospolya.spotifyapiwrapper.response.Artists;

import java.net.http.HttpRequest;
import java.util.List;

public final class GetArtists extends AbstractSpotifyRequest<Artists>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "artists";

    private GetArtists(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder
    {
        private List<String> artistIds;

        public Builder(List<String> artistIds) throws IllegalArgumentException
        {
            validateParametersNotNull(artistIds);
            this.artistIds = artistIds;
        }

        public GetArtists build()
        {
            SpotifyRequestBuilder spotifyRequestBuilder = new SpotifyRequestBuilder(REQUEST_URI_STRING);
            spotifyRequestBuilder.queryParam(IDS_QUERY_PARAM, artistIds);

            return new GetArtists(spotifyRequestBuilder.createGetRequests());
        }
    }
}
