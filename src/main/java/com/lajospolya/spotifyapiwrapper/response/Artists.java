package com.lajospolya.spotifyapiwrapper.response;

import java.util.List;

/**
 * @author Lajos Polya
 * Represent the response of GetArtist, and GetArtistsRelatedArtists as described at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
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
