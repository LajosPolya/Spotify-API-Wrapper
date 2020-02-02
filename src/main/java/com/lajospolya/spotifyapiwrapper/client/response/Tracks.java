package com.lajospolya.spotifyapiwrapper.client.response;

import java.util.List;

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
