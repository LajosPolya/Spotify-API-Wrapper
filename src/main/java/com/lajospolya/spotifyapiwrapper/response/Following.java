package com.lajospolya.spotifyapiwrapper.response;

/**
 * @author Lajos Polya
 * Represent the response of GetMeFollowing as described at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
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
