package com.lajospolya.spotifyapiwrapper.enumeration;

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
