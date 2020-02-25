package com.lajospolya.spotifyapiwrapper.enumeration;

public enum RecommendationSeedType
{
    ARTIST("artist"),
    TRACK("track"),
    GENRE("genre");

    private String name;

    RecommendationSeedType(String name)
    {
        this.name = name;
    }
}
