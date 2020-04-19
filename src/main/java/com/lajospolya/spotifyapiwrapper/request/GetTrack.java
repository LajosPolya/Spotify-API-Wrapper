package com.lajospolya.spotifyapiwrapper.request;

import com.lajospolya.spotifyapiwrapper.internal.ISpotifyRequestBuilder;
import com.lajospolya.spotifyapiwrapper.internal.SpotifyClientComponentsFactory;
import com.lajospolya.spotifyapiwrapper.response.Track;

/**
 * @author Lajos Polya
 *
 * Represents the endpoint at GET https://api.spotify.com/v1/tracks/{id} as descrbibed at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
public class GetTrack extends AbstractSpotifyRequest<Track>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "tracks/{id}";

    private GetTrack(ISpotifyRequestBuilder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder<GetTrack>
    {
        private String trackId;
        private String market;

        public Builder(String trackId) throws IllegalArgumentException
        {
            spotifyRequestParamValidationService.validateParametersNotNull(trackId);
            this.trackId = trackId;
        }

        @Override
        public GetTrack build()
        {
            ISpotifyRequestBuilder spotifyRequestBuilder = SpotifyClientComponentsFactory.spotifyRequestBuilder(REQUEST_URI_STRING, trackId);
            addOptionalQueryParams(spotifyRequestBuilder);

            return new GetTrack(spotifyRequestBuilder.GET());
        }

        private void addOptionalQueryParams(ISpotifyRequestBuilder requestUriBuilder)
        {
            if(market != null)
            {
                requestUriBuilder.queryParam(MARKET_QUERY_PARAM, market);
            }
        }

        public Builder market(String market)
        {
            this.market = market;
            return this;
        }
    }
}
