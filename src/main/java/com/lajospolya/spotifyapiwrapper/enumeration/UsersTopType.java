package com.lajospolya.spotifyapiwrapper.enumeration;

public enum UsersTopType
{
    artists("artists"),
    tracks("tracks");

    private String name;

    UsersTopType(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return this.name;
    }
}
