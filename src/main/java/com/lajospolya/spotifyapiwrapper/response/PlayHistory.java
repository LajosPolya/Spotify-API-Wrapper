package com.lajospolya.spotifyapiwrapper.response;

public class PlayHistory
{
    private Context context;
    private String played_at;
    private SimplifiedTrack track;

    public Context getContext()
    {
        return context;
    }

    public void setContext(Context context)
    {
        this.context = context;
    }

    public String getPlayed_at()
    {
        return played_at;
    }

    public void setPlayed_at(String played_at)
    {
        this.played_at = played_at;
    }

    public SimplifiedTrack getTrack()
    {
        return track;
    }

    public void setTrack(SimplifiedTrack track)
    {
        this.track = track;
    }
}
