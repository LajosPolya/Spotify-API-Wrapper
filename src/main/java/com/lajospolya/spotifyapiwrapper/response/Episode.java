package com.lajospolya.spotifyapiwrapper.response;

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
