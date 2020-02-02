package com.lajospolya.spotifyapiwrapper.client.response;

import java.util.List;

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
