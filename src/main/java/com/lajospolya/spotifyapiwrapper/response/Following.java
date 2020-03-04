package com.lajospolya.spotifyapiwrapper.response;

public class Following
{
    private PagingCursor<Artist> artists;

    public PagingCursor<Artist> getArtists()
    {
        return artists;
    }

    public void setArtists(PagingCursor<Artist> artists)
    {
        this.artists = artists;
    }
}
