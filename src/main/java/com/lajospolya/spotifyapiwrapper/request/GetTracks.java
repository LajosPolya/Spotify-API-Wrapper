package com.lajospolya.spotifyapiwrapper.request;

import com.lajospolya.spotifyapiwrapper.component.ISpotifyRequestBuilder;
import com.lajospolya.spotifyapiwrapper.component.SpotifyClientComponentsFactory;
import com.lajospolya.spotifyapiwrapper.response.Tracks;

import java.util.List;

/**
 * @author Lajos Polya
 *
 * Represents the endpoint at GET https://api.spotify.com/v1/tracks as descrbibed at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
public class GetTracks extends AbstractSpotifyRequest<Tracks>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "tracks";

    private GetTracks(ISpotifyRequestBuilder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder<GetTracks>
    {
        private final List<String> trackIds;
        private String market;

        public Builder(List<String> trackIds) throws IllegalArgumentException
        {
            spotifyRequestParamValidationService.validateParametersNotNull(trackIds);
            this.trackIds = trackIds;
        }

        @Override
        public GetTracks build()
        {
            ISpotifyRequestBuilder spotifyRequestBuilder = SpotifyClientComponentsFactory.spotifyRequestBuilder(REQUEST_URI_STRING)
            .queryParam(IDS_QUERY_PARAM, trackIds);
            addOptionalQueryParams(spotifyRequestBuilder);

            return new GetTracks(spotifyRequestBuilder.GET());
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
