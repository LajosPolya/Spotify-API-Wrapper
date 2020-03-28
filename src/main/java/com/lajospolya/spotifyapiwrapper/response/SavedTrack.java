package com.lajospolya.spotifyapiwrapper.response;

/**
 * @author Lajos Polya
 * Represent the response of GetMeTracks as described at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
public class SavedTrack
{
    private String added_at;
    private Track track;

    public String getAdded_at()
    {
        return added_at;
    }

    public void setAdded_at(String added_at)
    {
        this.added_at = added_at;
    }

    public Track getTrack()
    {
        return track;
    }

    public void setTrack(Track track)
    {
        this.track = track;
    }
}
