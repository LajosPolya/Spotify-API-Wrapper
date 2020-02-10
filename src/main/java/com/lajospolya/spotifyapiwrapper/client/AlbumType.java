package com.lajospolya.spotifyapiwrapper.client;

public enum AlbumType
{
    ALBUM("album"),
    SINGLE("single"),
    APPEARS_ON("appears_on"),
    COMPILATION("compilation");

    AlbumType(String type)
    {
        this.type = type;
    }

    String type;

    public String getType()
    {
        return this.type;
    }
}
