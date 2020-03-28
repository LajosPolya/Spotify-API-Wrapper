package com.lajospolya.spotifyapiwrapper.response;

/**
 * @author Lajos Polya
 * Represent the response of DeletePlaylistsTracks, PostPlaylistsTracksAdd, and PutPlaylistsTracksReorder as described
 * at https://developer.spotify.com/documentation/web-api/reference-beta/
 */
public class PlaylistSnapshot
{
    private String snapshot_id;

    public String getSnapshot_id()
    {
        return snapshot_id;
    }

    public void setSnapshot_id(String snapshot_id)
    {
        this.snapshot_id = snapshot_id;
    }
}
