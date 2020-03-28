package com.lajospolya.spotifyapiwrapper.response;

import com.lajospolya.spotifyapiwrapper.cachable.CacheableResponse;

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
