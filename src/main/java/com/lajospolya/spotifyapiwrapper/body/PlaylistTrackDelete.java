package com.lajospolya.spotifyapiwrapper.body;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.stream.Collectors;

public class PlaylistTrackDelete
{
    private List<URI> tracks;
    @SerializedName("snapshot_id")
    private String snapshotId;

    public PlaylistTrackDelete(List<String> tracks, String snapshotId)
    {
        this.tracks = tracks.stream().map(URI::new).collect(Collectors.toList());
        this.snapshotId = snapshotId;
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
