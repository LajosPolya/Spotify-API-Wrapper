package com.lajospolya.spotifyapiwrapper.response;

import java.util.Map;

/**
 * @author Lajos Polya
 * Represent the response of GetTrack and GetUsersTopTracks as described at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
public class Track extends SimplifiedTrack
{
    private SimplifiedAlbum album;
    private Map<String, String> external_ids;
    private Integer popularity;

    public SimplifiedAlbum getAlbum()
    {
        return album;
    }

    public void setAlbum(SimplifiedAlbum album)
    {
        this.album = album;
    }

    public Map<String, String> getExternal_ids()
    {
        return external_ids;
    }

    public void setExternal_ids(Map<String, String> external_ids)
    {
        this.external_ids = external_ids;
    }

    public Integer getPopularity()
    {
        return popularity;
    }

    public void setPopularity(Integer popularity)
    {
        this.popularity = popularity;
    }
}
