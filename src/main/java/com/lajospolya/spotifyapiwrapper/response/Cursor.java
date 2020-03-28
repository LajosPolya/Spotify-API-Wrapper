package com.lajospolya.spotifyapiwrapper.response;

/**
 * Spotify's implementation of a cursor
 * @author Lajos Polya
 */
public class Cursor
{
    private String after;

    public String getAfter()
    {
        return after;
    }

    public void setAfter(String after)
    {
        this.after = after;
    }
}
