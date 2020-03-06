package com.lajospolya.spotifyapiwrapper.body;

import java.util.List;

public class PlaylistTrackAdd
{
    private List<String> uris;
    private Integer position;

    public PlaylistTrackAdd(List<String> uris, Integer position)
    {
        this.uris = uris;
        this.position = position;
    }
}
