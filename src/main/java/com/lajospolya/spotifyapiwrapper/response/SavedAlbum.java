package com.lajospolya.spotifyapiwrapper.response;

public class SavedAlbum
{
    private String added_at;
    private Album album;

    public String getAdded_at()
    {
        return added_at;
    }

    public void setAdded_at(String added_at)
    {
        this.added_at = added_at;
    }

    public Album getAlbum()
    {
        return album;
    }

    public void setAlbum(Album album)
    {
        this.album = album;
    }
}
