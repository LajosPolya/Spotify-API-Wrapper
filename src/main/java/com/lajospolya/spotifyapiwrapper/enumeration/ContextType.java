package com.lajospolya.spotifyapiwrapper.enumeration;

public enum ContextType
{
    ALBUM("album"),
    ARTIST("artist"),
    PLAYLIST("playlist");

    ContextType(String type)
    {
        this.type = type;
    }

    String type;

    public String getType()
    {
        return this.type;
    }
}
