package com.lajospolya.spotifyapiwrapper.body;

public class PlaylistTrackReorder
{
    private Integer range_start;
    private Integer insert_before;
    private Integer range_length;
    private String snapshot_id;

    public PlaylistTrackReorder(Integer rangeStart, Integer insertBefore, Integer rangeLength, String snapshotId)
    {
        this.range_start = rangeStart;
        this.insert_before = insertBefore;
        this.range_length = rangeLength;
        this.snapshot_id = snapshotId;
    }
}
