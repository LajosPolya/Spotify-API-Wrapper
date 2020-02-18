package com.lajospolya.spotifyapiwrapper.enumeration;

public enum SearchItemType
{
    Album("album"),
    Artist("artist"),
    Playlist("playlist"),
    Track("track");

    private final String type;

    SearchItemType(String type)
    {
        this.type = type;
    }

    public String getType()
    {
        return this.type;
    }
}
