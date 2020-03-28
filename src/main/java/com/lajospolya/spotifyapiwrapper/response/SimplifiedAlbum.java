package com.lajospolya.spotifyapiwrapper.response;

/**
 * @author Lajos Polya
 * Represent the Album (Simplified) object as described at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
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
