package com.lajospolya.spotifyapiwrapper.spotifyrequest.service;

import java.util.List;

public interface ISpotifyRequestParamValidationService
{
    void validateAcousticness(Double acousticness) throws IllegalArgumentException;

    void validateDanceability(Double danceability) throws IllegalArgumentException;

    void validateDurationMs(Integer durationMs) throws IllegalArgumentException;

    void validateEnergy(Double energy) throws IllegalArgumentException;

    void validateInstrumentalness(Double intrumentalness) throws IllegalArgumentException;

    void validateKey(Integer key) throws IllegalArgumentException;

    void validateLiveness(Double liveness) throws IllegalArgumentException;

    void validateLoudness(Double loudness) throws IllegalArgumentException;

    void validateModality(Integer modality) throws IllegalArgumentException;

    void validatePopularity(Integer popularity) throws IllegalArgumentException;

    void validateSpeechiness(Double speechiness) throws IllegalArgumentException;

    void validateTempo(Double tempo) throws IllegalArgumentException;

    void validateTimeSignature(Integer timeSignature) throws IllegalArgumentException;

    void validateValence(Double valence) throws IllegalArgumentException;

    void validateList(List<?> list) throws IllegalArgumentException;

    void validateLimit50(Integer limit) throws IllegalArgumentException;

    void validateLimit100(Integer limit) throws IllegalArgumentException;

    void validateOffset(Integer offset) throws IllegalArgumentException;
}
