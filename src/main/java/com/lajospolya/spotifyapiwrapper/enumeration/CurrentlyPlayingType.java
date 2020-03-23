package com.lajospolya.spotifyapiwrapper.enumeration;

import com.google.gson.annotations.SerializedName;

public enum CurrentlyPlayingType
{
    @SerializedName("track")
    TRACK("track"),
    @SerializedName("episode")
    EPISODE("episode"),
    @SerializedName("ad")
    AD("ad"),
    @SerializedName("unknown")
    UNKNOWN("unknown");

    CurrentlyPlayingType(String type)
    {
        this.type = type;
    }

    private String type;

    public String getType()
    {
        return type;
    }
}
