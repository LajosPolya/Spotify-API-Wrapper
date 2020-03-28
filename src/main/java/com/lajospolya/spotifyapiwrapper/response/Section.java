package com.lajospolya.spotifyapiwrapper.response;

/**
 * @author Lajos Polya
 * Represent the Section object as described at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
public class Section extends TimeInterval
{
    private Float loudness;
    private Float tempo;
    private Float tempo_confidence;
    private Integer key;
    private Float key_confidence;
    private Integer mode;
    private Float mode_confidence;
    private Integer time_signature;
    private Float time_signature_confidence;

    public Float getLoudness()
    {
        return loudness;
    }

    public void setLoudness(Float loudness)
    {
        this.loudness = loudness;
    }

    public Float getTempo()
    {
        return tempo;
    }

    public void setTempo(Float tempo)
    {
        this.tempo = tempo;
    }

    public Float getTempo_confidence()
    {
        return tempo_confidence;
    }

    public void setTempo_confidence(Float tempo_confidence)
    {
        this.tempo_confidence = tempo_confidence;
    }

    public Integer getKey()
    {
        return key;
    }

    public void setKey(Integer key)
    {
        this.key = key;
    }

    public Float getKey_confidence()
    {
        return key_confidence;
    }

    public void setKey_confidence(Float key_confidence)
    {
        this.key_confidence = key_confidence;
    }

    public Integer getMode()
    {
        return mode;
    }

    public void setMode(Integer mode)
    {
        this.mode = mode;
    }

    public Float getMode_confidence()
    {
        return mode_confidence;
    }

    public void setMode_confidence(Float mode_confidence)
    {
        this.mode_confidence = mode_confidence;
    }

    public Integer getTime_signature()
    {
        return time_signature;
    }

    public void setTime_signature(Integer time_signature)
    {
        this.time_signature = time_signature;
    }

    public Float getTime_signature_confidence()
    {
        return time_signature_confidence;
    }

    public void setTime_signature_confidence(Float time_signature_confidence)
    {
        this.time_signature_confidence = time_signature_confidence;
    }
}
