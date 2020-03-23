package com.lajospolya.spotifyapiwrapper.enumeration;

import com.google.gson.annotations.SerializedName;

public enum RepeatState
{
    @SerializedName("track")
    TRACK("track"),
    @SerializedName("context")
    CONTEXT("context"),
    @SerializedName("off")
    OFF("off");

    private String state;

    RepeatState(String state)
    {
        this.state = state;
    }

    public String getState()
    {
        return state;
    }
}
