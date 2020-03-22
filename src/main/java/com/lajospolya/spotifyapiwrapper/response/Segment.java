package com.lajospolya.spotifyapiwrapper.response;

import java.util.List;

public class Segment extends TimeInterval
{
    private Float loudness_start;
    private Float loudness_max;
    private Float loudness_max_time;
    private Float loudness_end;
    private List<Float> pitches;
    private List<Float> timbre;

    public Float getLoudness_start()
    {
        return loudness_start;
    }

    public void setLoudness_start(Float loudness_start)
    {
        this.loudness_start = loudness_start;
    }

    public Float getLoudness_max()
    {
        return loudness_max;
    }

    public void setLoudness_max(Float loudness_max)
    {
        this.loudness_max = loudness_max;
    }

    public Float getLoudness_max_time()
    {
        return loudness_max_time;
    }

    public void setLoudness_max_time(Float loudness_max_time)
    {
        this.loudness_max_time = loudness_max_time;
    }

    public Float getLoudness_end()
    {
        return loudness_end;
    }

    public void setLoudness_end(Float loudness_end)
    {
        this.loudness_end = loudness_end;
    }

    public List<Float> getPitches()
    {
        return pitches;
    }

    public void setPitches(List<Float> pitches)
    {
        this.pitches = pitches;
    }

    public List<Float> getTimbre()
    {
        return timbre;
    }

    public void setTimbre(List<Float> timbre)
    {
        this.timbre = timbre;
    }
}
