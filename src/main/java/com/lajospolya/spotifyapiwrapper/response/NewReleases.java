package com.lajospolya.spotifyapiwrapper.response;

/**
 * @author Lajos Polya
 * Represent the response of GetAllNewReleases as described at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
public class NewReleases
{
    Paging<Album> albums;

    public Paging<Album> getAlbums()
    {
        return albums;
    }

    public void setAlbums(Paging<Album> albums)
    {
        this.albums = albums;
    }
}
