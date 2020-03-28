package com.lajospolya.spotifyapiwrapper.response;

public class Following extends CacheableResponse
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
