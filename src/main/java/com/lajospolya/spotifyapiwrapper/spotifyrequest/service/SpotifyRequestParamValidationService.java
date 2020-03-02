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

    public void validateInstrumentalness(Double instrumentalness) throws IllegalArgumentException
    {
        if(instrumentalness == null || instrumentalness < 0.0 || instrumentalness > 1.0)
        {
            throw new IllegalArgumentException("instrumentalness must be between 0.0 and 1.0 inclusive");
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
            throw new IllegalArgumentException("modality must be 0 or 1");
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
            throw new IllegalArgumentException("time signature must be positive");
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
        if(list == null || list.isEmpty())
        {
            throw new IllegalArgumentException("list must not be null or empty");
        }
    }

    public void validateLimit50(Integer limit) throws IllegalArgumentException
    {
        if(limit == null || limit < 1 || limit > 50)
        {
            throw new IllegalArgumentException("limit must be between 1 and 50 inclusive");
        }
    }

    public void validateLimit100(Integer limit) throws IllegalArgumentException
    {
        if(limit == null || limit < 1 || limit > 100)
        {
            throw new IllegalArgumentException("limit must be between 1 and 100 inclusive");
        }
    }

    public void validateOffset(Integer offset) throws IllegalArgumentException
    {
        if(offset == null || offset < 0)
        {
            throw new IllegalArgumentException("offset must be positive");
        }
    }

    public void validateUserIds(List<String> userIds) throws IllegalArgumentException
    {
        if(userIds == null)
        {
            throw new IllegalArgumentException("userIds cannot be null");
        }
        else if(userIds.size() > 5)
        {
            throw new IllegalArgumentException("Cannot use more than 5 userIds");
        }
    }
}
