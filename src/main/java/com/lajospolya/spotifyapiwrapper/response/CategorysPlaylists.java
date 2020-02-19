package com.lajospolya.spotifyapiwrapper.response;

public class CategorysPlaylists
{
    Paging<Playlist> playlists;

    public Paging<Playlist> getPlaylists()
    {
        return playlists;
    }

    public void setPlaylists(Paging<Playlist> playlists)
    {
        this.playlists = playlists;
    }
}
