package com.lajospolya.spotifyapiwrapper.enumeration;

import com.google.gson.annotations.SerializedName;

public enum DeviceType
{
    @SerializedName("Computer")
    COMPUTER("Computer"),
    @SerializedName("Smartphone")
    SMARTPHONE("Smartphone"),
    @SerializedName("Speaker")
    SPEAKER("Speaker");

    private String type;

    DeviceType(String type)
    {
        this.type = type;
    }
}
