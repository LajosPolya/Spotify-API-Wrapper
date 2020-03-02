package com.lajospolya.spotifyapiwrapper.enumeration;

public enum FollowType
{
    artist("artist"),
    user("user");

    private String name;

    FollowType(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return this.name;
    }
}
