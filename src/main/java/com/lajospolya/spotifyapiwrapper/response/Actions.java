package com.lajospolya.spotifyapiwrapper.response;

/**
 * @author Lajos Polya
 * Represent a set of Spotify Actions as described at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
public class Actions
{
    private Disallows disallows;

    public Disallows getDisallows()
    {
        return disallows;
    }

    public void setDisallows(Disallows disallows)
    {
        this.disallows = disallows;
    }
}
