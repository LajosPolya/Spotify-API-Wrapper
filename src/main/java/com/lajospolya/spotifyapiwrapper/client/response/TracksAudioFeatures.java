package com.lajospolya.spotifyapiwrapper.client.response;

import java.util.List;

public class TracksAudioFeatures
{
    List<AudioFeatures> audio_features;

    public List<AudioFeatures> getAudio_features()
    {
        return audio_features;
    }

    public void setAudio_features(List<AudioFeatures> audio_features)
    {
        this.audio_features = audio_features;
    }
}
