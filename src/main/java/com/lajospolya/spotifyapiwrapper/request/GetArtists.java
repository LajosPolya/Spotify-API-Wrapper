package com.lajospolya.spotifyapiwrapper.request;

import com.lajospolya.spotifyapiwrapper.response.Artists;

import java.net.http.HttpRequest;
import java.util.List;

public class GetArtists extends AbstractSpotifyRequest<Artists>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "artists";

    private GetArtists(HttpRequest.Builder requestBuilder)
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
            SpotifyRequestBuilder spotifyRequestBuilder = new SpotifyRequestBuilder(REQUEST_URI_STRING);
            spotifyRequestBuilder.queryParam(IDS_QUERY_PARAM, artistIds);

            return new GetArtists(spotifyRequestBuilder.createGetRequests());
        }
    }
}
