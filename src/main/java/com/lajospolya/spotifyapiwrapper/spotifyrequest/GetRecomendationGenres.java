package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import com.lajospolya.spotifyapiwrapper.response.RecommendationGenres;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.http.HttpRequest;

public class GetRecomendationGenres extends AbstractSpotifyRequest<RecommendationGenres>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "recommendations/available-genre-seeds";

    private GetRecomendationGenres(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder
    {
        public Builder() throws IllegalArgumentException {}

        public GetRecomendationGenres build()
        {
            UriComponentsBuilder requestUriBuilder =  UriComponentsBuilder.fromUriString(REQUEST_URI_STRING);

            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(requestUriBuilder.build().toUri())
                    .GET();
            return new GetRecomendationGenres(requestBuilder);
        }
    }
}
