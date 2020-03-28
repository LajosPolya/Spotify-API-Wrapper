package com.lajospolya.spotifyapiwrapper.request;

import com.lajospolya.spotifyapiwrapper.response.Episode;

import java.net.http.HttpRequest;

/**
 * @author Lajos Polya
 *
 * Represents the endpoint at GET https://api.spotify.com/v1/episodes/{id} as descrbibed at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
public class GetEpisode extends AbstractSpotifyRequest<Episode>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "episodes/{id}";

    private GetEpisode(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder<GetEpisode>
    {
        private String episodeId;

        private String market;

        public Builder(String episodeId)
        {
            spotifyRequestParamValidationService.validateParametersNotNull(episodeId);
            this.episodeId = episodeId;
        }

        @Override
        public GetEpisode build()
        {
            SpotifyRequestBuilder spotifyRequestBuilder = new SpotifyRequestBuilder(REQUEST_URI_STRING, episodeId);
            addOptionalQueryParams(spotifyRequestBuilder);
            return new GetEpisode(spotifyRequestBuilder.createGetRequests());
        }

        private void addOptionalQueryParams(SpotifyRequestBuilder requestUriBuilder)
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
