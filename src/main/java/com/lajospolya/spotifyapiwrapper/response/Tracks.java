package com.lajospolya.spotifyapiwrapper.response;

import java.util.List;

/**
 * @author Lajos Polya
 * Represent the response of GetTracks as described at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
public class Tracks
{
    List<Track> tracks;

    public List<Track> getTracks()
    {
        return tracks;
    }

    public void setTracks(List<Track> tracks)
    {
        this.tracks = tracks;
    }
}
