package com.lajospolya.spotifyapiwrapper.response;

/**
 * @author Lajos Polya
 * Represent the response of GetFeaturedPlaylists as described at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
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
