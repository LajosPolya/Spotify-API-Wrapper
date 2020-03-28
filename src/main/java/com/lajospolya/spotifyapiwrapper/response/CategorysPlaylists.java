package com.lajospolya.spotifyapiwrapper.response;

/**
 * @author Lajos Polya
 * Represent the response of GetCategorysPlaylists as described at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
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
