package com.lajospolya.spotifyapiwrapper.enumeration;

public enum RepeatState
{
    TRACK("track"),
    CONTEXT("context"),
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
