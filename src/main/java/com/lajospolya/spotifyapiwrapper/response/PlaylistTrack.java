package com.lajospolya.spotifyapiwrapper.response;

public class PlaylistTrack
{
    private String added_at;
    private UserPublic added_by;
    private Boolean is_local;
    private Track track;

    public String getAdded_at()
    {
        return added_at;
    }

    public void setAdded_at(String added_at)
    {
        this.added_at = added_at;
    }

    public UserPublic getAdded_by()
    {
        return added_by;
    }

    public void setAdded_by(UserPublic added_by)
    {
        this.added_by = added_by;
    }

    public Boolean getIs_local()
    {
        return is_local;
    }

    public void setIs_local(Boolean is_local)
    {
        this.is_local = is_local;
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
