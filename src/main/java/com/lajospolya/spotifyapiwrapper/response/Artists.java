package com.lajospolya.spotifyapiwrapper.response;

import java.util.List;

public class Artists
{
    List<Artist> artists;

    public List<Artist> getArtists()
    {
        return artists;
    }

    public void setArtists(List<Artist> artists)
    {
        this.artists = artists;
    }
}
