package com.lajospolya.spotifyapiwrapper.enumeration;

public enum TimeRange
{
    long_term("long_term"),
    medium_term("medium_term"),
    short_term("short_term");

    private String name;

    TimeRange(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return this.name;
    }
}
