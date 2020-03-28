package com.lajospolya.spotifyapiwrapper.response;

/**
 * @author Lajos Polya
 * Represent the response of GetEpisode as described at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
public class Episode extends SimplifiedEpisode
{
    private SimplifiedShow show;

    public SimplifiedShow getShow()
    {
        return show;
    }

    public void setShow(SimplifiedShow show)
    {
        this.show = show;
    }
}
