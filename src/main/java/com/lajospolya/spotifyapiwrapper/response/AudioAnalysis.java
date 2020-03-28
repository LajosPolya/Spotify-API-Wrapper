package com.lajospolya.spotifyapiwrapper.response;

import java.util.List;

/**
 * @author Lajos Polya
 * Represent the response of GetAudioAnalysis as described at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
public class AudioAnalysis
{
    private Meta meta;
    private List<TimeInterval> bars;
    private List<TimeInterval> beats;
    private List<Section> sections;
    private List<Segment> segments;
    private List<TimeInterval> tatums;
    private AudioAnalysisTrack track;

    public Meta getMeta()
    {
        return meta;
    }

    public void setMeta(Meta meta)
    {
        this.meta = meta;
    }

    public List<TimeInterval> getBars()
    {
        return bars;
    }

    public void setBars(List<TimeInterval> bars)
    {
        this.bars = bars;
    }

    public List<TimeInterval> getBeats()
    {
        return beats;
    }

    public void setBeats(List<TimeInterval> beats)
    {
        this.beats = beats;
    }

    public List<Section> getSections()
    {
        return sections;
    }

    public void setSections(List<Section> sections)
    {
        this.sections = sections;
    }

    public List<Segment> getSegments()
    {
        return segments;
    }

    public void setSegments(List<Segment> segments)
    {
        this.segments = segments;
    }

    public List<TimeInterval> getTatums()
    {
        return tatums;
    }

    public void setTatums(List<TimeInterval> tatums)
    {
        this.tatums = tatums;
    }

    public AudioAnalysisTrack getTrack()
    {
        return track;
    }

    public void setTrack(AudioAnalysisTrack track)
    {
        this.track = track;
    }
}
