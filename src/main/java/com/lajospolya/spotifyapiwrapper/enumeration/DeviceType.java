package com.lajospolya.spotifyapiwrapper.enumeration;

import com.google.gson.annotations.SerializedName;

public enum DeviceType
{
    @SerializedName("Computer")
    COMPUTER("Computer"),
    @SerializedName("Tablet")
    TABLET("Tablet"),
    @SerializedName("Smartphone")
    SMARTPHONE("Smartphone"),
    @SerializedName("Speaker")
    SPEAKER("Speaker"),
    @SerializedName("TV")
    TV("TV"),
    @SerializedName("AVR")
    AVR("AVR"),
    @SerializedName("STB")
    STB("STB"),
    @SerializedName("AudioDongle")
    AUDIODONGLE("AudioDongle"),
    @SerializedName("GameConsole")
    GAMECONSOLE("GameConsole"),
    @SerializedName("CastVideo")
    CASTVIDEO("CastVideo"),
    @SerializedName("CastAudio")
    CASTAUDIO("CastAudio"),
    @SerializedName("Automobile")
    AUTOMOBILE("Automobile"),
    @SerializedName("Unknown")
    UNKNOWN("Unknown");

    private String type;

    DeviceType(String type)
    {
        this.type = type;
    }
}
