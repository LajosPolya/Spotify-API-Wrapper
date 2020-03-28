package com.lajospolya.spotifyapiwrapper.response;

import java.util.List;

/**
 * @author Lajos Polya
 * Represent the response of GetRecommendationGenres as described at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
public class RecommendationGenres
{
    List<String> genres;

    public List<String> getGenres()
    {
        return genres;
    }

    public void setGenres(List<String> genres)
    {
        this.genres = genres;
    }
}
