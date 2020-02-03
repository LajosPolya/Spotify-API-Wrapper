package com.lajospolya.spotifyapiwrapper.client.response;

public class SimplifiedAlbum extends AlbumBase
{
    private String album_group;

    public String getAlbum_group()
    {
        return album_group;
    }

    public void setAlbum_group(String album_group)
    {
        this.album_group = album_group;
    }
}
