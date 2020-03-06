package com.lajospolya.spotifyapiwrapper.body;

public class PlatlistTrackReorder
{
    private Integer range_start;
    private Integer insert_before;
    private Integer range_length;
    private String snapshot_id;

    public PlatlistTrackReorder(Integer rangeStart, Integer insertBefore, Integer rangeLength, String snapshotId)
    {
        this.range_start = rangeStart;
        this.insert_before = insertBefore;
        this.range_length = rangeLength;
        this.snapshot_id = snapshotId;
    }
}
