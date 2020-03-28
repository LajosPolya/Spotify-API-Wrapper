package com.lajospolya.spotifyapiwrapper.response;

import java.util.List;

/**
 * @author Lajos Polya
 * Represent the response of GetSeveralAudioFeatures as described at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
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
