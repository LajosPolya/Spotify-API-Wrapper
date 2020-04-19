package com.lajospolya.spotifyapiwrapper.request;

import com.lajospolya.spotifyapiwrapper.component.ISpotifyRequestBuilder;
import com.lajospolya.spotifyapiwrapper.component.SpotifyClientComponentsFactory;
import com.lajospolya.spotifyapiwrapper.response.RecommendationGenres;

/**
 * @author Lajos Polya
 *
 * Represents the endpoint at GET https://api.spotify.com/v1/recommendations/available-genre-seeds as descrbibed at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
public class GetRecomendationGenres extends AbstractSpotifyRequest<RecommendationGenres>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "recommendations/available-genre-seeds";

    private GetRecomendationGenres(ISpotifyRequestBuilder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder<GetRecomendationGenres>
    {
        public Builder() {}

        @Override
        public GetRecomendationGenres build()
        {
            ISpotifyRequestBuilder spotifyRequestBuilder = SpotifyClientComponentsFactory.spotifyRequestBuilder(REQUEST_URI_STRING);

            return new GetRecomendationGenres(spotifyRequestBuilder.GET());
        }
    }
}
