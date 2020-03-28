package com.lajospolya.spotifyapiwrapper.response;

/**
 * @author Lajos Polya
 * Represent the Playlists Tracks object as described at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
public class PlaylistsTracks
{
    private String href;
    private Integer total;

    public String getHref()
    {
        return href;
    }

    public void setHref(String href)
    {
        this.href = href;
    }

    public Integer getTotal()
    {
        return total;
    }

    public void setTotal(Integer total)
    {
        this.total = total;
    }
}
