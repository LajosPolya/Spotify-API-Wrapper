package com.lajospolya.spotifyapiwrapper.response;

/**
 * @author Lajos Polya
 * Represent the Time Interval object as described at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
public class TimeInterval
{
    private Float start;
    private Float duration;
    private Float confidence;

    public Float getStart()
    {
        return start;
    }

    public void setStart(Float start)
    {
        this.start = start;
    }

    public Float getDuration()
    {
        return duration;
    }

    public void setDuration(Float duration)
    {
        this.duration = duration;
    }

    public Float getConfidence()
    {
        return confidence;
    }

    public void setConfidence(Float confidence)
    {
        this.confidence = confidence;
    }
}
