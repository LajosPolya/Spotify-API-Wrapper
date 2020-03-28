package com.lajospolya.spotifyapiwrapper.response;

import java.util.List;

/**
 * @author Lajos Polya
 * Represent the response of GetAlbums request as described at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
public class Albums
{
    private List<Album> albums;

    public List<Album> getAlbums()
    {
        return albums;
    }

    public void setAlbums(List<Album> albums)
    {
        this.albums = albums;
    }
}
