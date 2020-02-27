package com.lajospolya.spotifyapiwrapper.response;

public class FeaturedPlaylists
{
    private String message;
    private Paging<Playlist> playlists;

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public Paging<Playlist> getPlaylists()
    {
        return playlists;
    }

    public void setPlaylists(Paging<Playlist> playlists)
    {
        this.playlists = playlists;
    }
}
