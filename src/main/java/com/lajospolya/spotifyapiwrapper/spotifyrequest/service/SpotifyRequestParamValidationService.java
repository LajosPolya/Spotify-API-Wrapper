package com.lajospolya.spotifyapiwrapper.spotifyrequest.service;

import java.util.List;

public class SpotifyRequestParamValidationService implements ISpotifyRequestParamValidationService
{
    public void validateAcousticness(Double acousticness) throws IllegalArgumentException
    {
        if(acousticness == null || acousticness < 0.0 || acousticness > 1.0)
        {
            throw new IllegalArgumentException("acousticness must be between 0.0 and 1.0 inclusive");
        }
    }

    public void validateDanceability(Double danceability) throws IllegalArgumentException
    {
        if(danceability == null || danceability < 0.0 || danceability > 1.0)
        {
            throw new IllegalArgumentException("danceability must be between 0.0 and 1.0 inclusive");
        }
    }

    public void validateDurationMs(Integer durationMs) throws IllegalArgumentException
    {
        if(durationMs == null || durationMs < 0)
        {
            throw new IllegalArgumentException("duration_ms must be positive");
        }
    }

    public void validateEnergy(Double energy) throws IllegalArgumentException
    {
        if(energy == null || energy < 0.0 || energy > 1.0)
        {
            throw new IllegalArgumentException("energy must be between 0.0 and 1.0 inclusive");
        }
    }

    public void validateInstrumentalness(Double intrumentalness) throws IllegalArgumentException
    {
        if(intrumentalness == null || intrumentalness < 0.0 || intrumentalness > 1.0)
        {
            throw new IllegalArgumentException("intrumentalness must be between 0.0 and 1.0 inclusive");
        }
    }

    public void validateKey(Integer key) throws IllegalArgumentException
    {
        if(key == null)
        {
            throw new IllegalArgumentException("key cannot be null");
        }
    }

    public void validateLiveness(Double liveness) throws IllegalArgumentException
    {
        if(liveness == null || liveness < 0.0 || liveness > 1.0)
        {
            throw new IllegalArgumentException("liveness must be between 0.0 and 1.0 inclusive");
        }
    }

    public void validateLoudness(Double loudness) throws IllegalArgumentException
    {
        if(loudness == null || loudness < 0.0)
        {
            throw new IllegalArgumentException("loudness must be greater than 0.0");
        }
    }

    public void validateModality(Integer modality) throws IllegalArgumentException
    {
        if(modality == null || (modality != 0 && modality != 1))
        {
            throw new IllegalArgumentException("modality modality must be 0 or 1");
        }
    }

    public void validatePopularity(Integer popularity) throws IllegalArgumentException
    {
        if(popularity == null || popularity < 0 || popularity > 100)
        {
            throw new IllegalArgumentException("popularity must be between 0 and 100 inclusive");
        }
    }

    public void validateSpeechiness(Double speechiness) throws IllegalArgumentException
    {
        if(speechiness == null || speechiness < 0.0 || speechiness > 1.0)
        {
            throw new IllegalArgumentException("speechiness must be between 0.0 and 1.0 inclusive");
        }
    }

    public void validateTempo(Double tempo) throws IllegalArgumentException
    {
        if(tempo == null || tempo < 0.0)
        {
            throw new IllegalArgumentException("tempo must be greater than 0.0");
        }
    }

    public void validateTimeSignature(Integer timeSignature) throws IllegalArgumentException
    {
        if(timeSignature == null || timeSignature < 0)
        {
            throw new IllegalArgumentException("time signature must be greater than 0");
        }
    }

    public void validateValence(Double valence) throws IllegalArgumentException
    {
        if(valence == null || valence < 0.0 || valence > 1.0)
        {
            throw new IllegalArgumentException("valence must be between 0.0 and 1.0 inclusive");
        }
    }

    public void validateList(List<?> list) throws IllegalArgumentException
    {
        if(list == null)
        {
            throw new IllegalArgumentException("list cannot be null");
        }
    }
}
