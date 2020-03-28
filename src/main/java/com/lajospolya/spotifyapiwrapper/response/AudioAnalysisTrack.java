package com.lajospolya.spotifyapiwrapper.response;

/**
 * @author Lajos Polya
 * Represent an Audio Analysis object as described at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
public class AudioAnalysisTrack
{
    private Integer num_samples;
    private Float duration;
    private String sample_md5;
    private Integer offset_seconds;
    private Integer window_seconds;
    private Integer analysis_sample_rate;
    private Integer analysis_channels;
    private Float end_of_fade_in;
    private Float start_of_fade_out;
    private Float loudness;
    private Float tempo;
    private Float tempo_confidence;
    private Integer time_signature;
    private Float time_signature_confidence;
    private Integer key;
    private Float key_confidence;
    private Integer mode;
    private Float mode_confidence;
    private String codestring;
    private String code_version;
    private String echoprintstring;
    private String echoprint_version;
    private String synchstring;
    private String synch_version;
    private String rhythmstring;
    private String rhythm_version;

    public Integer getNum_samples()
    {
        return num_samples;
    }

    public void setNum_samples(Integer num_samples)
    {
        this.num_samples = num_samples;
    }

    public Float getDuration()
    {
        return duration;
    }

    public void setDuration(Float duration)
    {
        this.duration = duration;
    }

    public String getSample_md5()
    {
        return sample_md5;
    }

    public void setSample_md5(String sample_md5)
    {
        this.sample_md5 = sample_md5;
    }

    public Integer getOffset_seconds()
    {
        return offset_seconds;
    }

    public void setOffset_seconds(Integer offset_seconds)
    {
        this.offset_seconds = offset_seconds;
    }

    public Integer getWindow_seconds()
    {
        return window_seconds;
    }

    public void setWindow_seconds(Integer window_seconds)
    {
        this.window_seconds = window_seconds;
    }

    public Integer getAnalysis_sample_rate()
    {
        return analysis_sample_rate;
    }

    public void setAnalysis_sample_rate(Integer analysis_sample_rate)
    {
        this.analysis_sample_rate = analysis_sample_rate;
    }

    public Integer getAnalysis_channels()
    {
        return analysis_channels;
    }

    public void setAnalysis_channels(Integer analysis_channels)
    {
        this.analysis_channels = analysis_channels;
    }

    public Float getEnd_of_fade_in()
    {
        return end_of_fade_in;
    }

    public void setEnd_of_fade_in(Float end_of_fade_in)
    {
        this.end_of_fade_in = end_of_fade_in;
    }

    public Float getStart_of_fade_out()
    {
        return start_of_fade_out;
    }

    public void setStart_of_fade_out(Float start_of_fade_out)
    {
        this.start_of_fade_out = start_of_fade_out;
    }

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

    public String getCodestring()
    {
        return codestring;
    }

    public void setCodestring(String codestring)
    {
        this.codestring = codestring;
    }

    public String getCode_version()
    {
        return code_version;
    }

    public void setCode_version(String code_version)
    {
        this.code_version = code_version;
    }

    public String getEchoprintstring()
    {
        return echoprintstring;
    }

    public void setEchoprintstring(String echoprintstring)
    {
        this.echoprintstring = echoprintstring;
    }

    public String getEchoprint_version()
    {
        return echoprint_version;
    }

    public void setEchoprint_version(String echoprint_version)
    {
        this.echoprint_version = echoprint_version;
    }

    public String getSynchstring()
    {
        return synchstring;
    }

    public void setSynchstring(String synchstring)
    {
        this.synchstring = synchstring;
    }

    public String getSynch_version()
    {
        return synch_version;
    }

    public void setSynch_version(String synch_version)
    {
        this.synch_version = synch_version;
    }

    public String getRhythmstring()
    {
        return rhythmstring;
    }

    public void setRhythmstring(String rhythmstring)
    {
        this.rhythmstring = rhythmstring;
    }

    public String getRhythm_version()
    {
        return rhythm_version;
    }

    public void setRhythm_version(String rhythm_version)
    {
        this.rhythm_version = rhythm_version;
    }
}
