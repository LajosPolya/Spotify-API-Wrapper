package com.lajospolya.spotifyapiwrapper.response;

import java.util.List;

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
