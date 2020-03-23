package com.lajospolya.spotifyapiwrapper.request;

import com.lajospolya.spotifyapiwrapper.response.RecommendationGenres;

import java.net.http.HttpRequest;

public final class GetRecomendationGenres extends AbstractSpotifyRequest<RecommendationGenres>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "recommendations/available-genre-seeds";

    private GetRecomendationGenres(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder<GetRecomendationGenres>
    {
        public Builder() {}

        @Override
        public GetRecomendationGenres build()
        {
            SpotifyRequestBuilder spotifyRequestBuilder = new SpotifyRequestBuilder(REQUEST_URI_STRING);

            return new GetRecomendationGenres(spotifyRequestBuilder.createGetRequests());
        }
    }
}
