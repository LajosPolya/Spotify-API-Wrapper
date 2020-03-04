package com.lajospolya.spotifyapiwrapper.enumeration;

public enum FollowingType
{
    artist("artist");

    private String name;

    FollowingType(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return this.name;
    }
}
