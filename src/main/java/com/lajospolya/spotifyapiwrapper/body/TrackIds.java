package com.lajospolya.spotifyapiwrapper.body;

import java.util.List;
import java.util.stream.Collectors;

public class TrackIds
{
    private List<URI> tracks;

    public TrackIds(List<String> tracks)
    {
        this.tracks = tracks.stream().map(URI::new).collect(Collectors.toList());
    }

    private class URI
    {
        private String uri;

        private URI(String uri)
        {
            this.uri = uri;
        }
    }
}
