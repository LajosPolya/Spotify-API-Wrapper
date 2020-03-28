package com.lajospolya.spotifyapiwrapper.response;

import java.util.List;

/**
 * @author Lajos Polya
 * Represent the response of GetShows as described at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
public class Shows
{
    private List<Show> shows;

    public List<Show> getShows()
    {
        return shows;
    }

    public void setShows(List<Show> shows)
    {
        this.shows = shows;
    }
}
