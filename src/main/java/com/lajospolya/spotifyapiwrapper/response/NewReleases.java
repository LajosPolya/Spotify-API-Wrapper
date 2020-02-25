package com.lajospolya.spotifyapiwrapper.response;

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
