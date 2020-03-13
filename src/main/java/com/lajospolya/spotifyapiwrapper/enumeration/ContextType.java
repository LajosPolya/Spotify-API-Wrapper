package com.lajospolya.spotifyapiwrapper.enumeration;

import com.google.gson.annotations.SerializedName;

public enum ContextType
{
    @SerializedName("album")
    ALBUM("album"),
    @SerializedName("artist")
    ARTIST("artist"),
    @SerializedName("track")
    TRACK("artist"),
    @SerializedName("playlist")
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
