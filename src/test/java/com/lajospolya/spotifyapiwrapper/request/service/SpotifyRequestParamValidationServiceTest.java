package com.lajospolya.spotifyapiwrapper.request.service;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SpotifyRequestParamValidationServiceTest
{
    SpotifyRequestParamValidationService spotifyRequestParamValidationService = new SpotifyRequestParamValidationService();

    private static final String ACOUSTIC_MESSAGE = "acousticness must be between 0.0 and 1.0 inclusive";
    private static final String DANCEABLITY_MESSAGE = "danceability must be between 0.0 and 1.0 inclusive";
    private static final String DURATION_MS_MESSAGE = "duration_ms must be positive";
    private static final String ENERGY_MS_MESSAGE = "energy must be between 0.0 and 1.0 inclusive";
    private static final String INSTRUMENTALNESS_MS_MESSAGE = "instrumentalness must be between 0.0 and 1.0 inclusive";
    private static final String KEY_MESSAGE = "key cannot be null";
    private static final String LIVENESS_MESSAGE = "liveness must be between 0.0 and 1.0 inclusive";
    private static final String LOUDNESS_MESSAGE = "loudness must be greater than 0.0";
    private static final String MODALITY_MESSAGE = "modality must be 0 or 1";
    private static final String POPULARITY_MESSAGE = "popularity must be between 0 and 100 inclusive";
    private static final String SPEECHINESS_MESSAGE = "speechiness must be between 0.0 and 1.0 inclusive";
    private static final String TEMPO_MESSAGE = "tempo must be greater than 0.0";
    private static final String TIME_SIGNATURE_MESSAGE = "time signature must be positive";
    private static final String VALENCE_MESSAGE = "valence must be between 0.0 and 1.0 inclusive";
    private static final String LIST_MESSAGE = "list must not be null or empty";
    private static final String LIMIT_50_MESSAGE = "limit must be between 1 and 50 inclusive";
    private static final String LIMIT_100_MESSAGE = "limit must be between 1 and 100 inclusive";
    private static final String OFFSET_MESSAGE = "offset must be positive";
    private static final String USER_IDS_NULL_MESSAGE = "userIds cannot be null";
    private static final String USER_IDS_TOO_LONG_MESSAGE = "Cannot use more than 5 userIds";
    private static final String FOLLOW_IDS_EMPTY_MESSAGE = "ids cannot be empty";
    private static final String FOLLOW_IDS_TOO_LONG_MESSAGE = "Cannot use more than 50 ids";
    private static final String PLAYLIST_URIS_EMPTY_MESSAGE = "uris cannot be empty";
    private static final String PLAYLIST_URIS_TOO_LONG_MESSAGE = "Cannot use more than 100 uris";
    private static final String TRACK_URIS_EMPTY_MESSAGE = "track uris cannot be empty";
    private static final String TRACK_URIS_TOO_LONG_MESSAGE = "Cannot use more than 100 track uris";
    private static final String VOLUME_INVALID_MESSAGE = "volume must be between 0 and 100 inclusive";
    private static final String NULL_PARAM_MESSAGE = "Parameters cannot be null";

    @Test
    void verify_validateParametersNotNull_throwsExceptionOnNullParameter()
    {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->
                spotifyRequestParamValidationService.validateParametersNotNull((Object) null));

        assertEquals(e.getMessage(), NULL_PARAM_MESSAGE);
    }

    @Test
    void verify_validateParametersNotNull_throwsExceptionWhenOneOfManyParamsNull()
    {
        Integer param = 456;
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->
                spotifyRequestParamValidationService.validateParametersNotNull(param, null));

        assertEquals(e.getMessage(), NULL_PARAM_MESSAGE);
    }

    @Test
    void verify_validateParametersNotNull_isSuccessfulOnNonNullParam()
    {
        String param = "string param";
        spotifyRequestParamValidationService.validateParametersNotNull(param);
    }

    @Test
    void verify_validateAcousticness_throwsExceptionOnNullParameter()
    {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->
                spotifyRequestParamValidationService.validateAcousticness(null));

        assertEquals(e.getMessage(), ACOUSTIC_MESSAGE);
    }

    @Test
    void verify_validateAcousticness_throwsExceptionWhenOnLowerBound()
    {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->
                spotifyRequestParamValidationService.validateAcousticness(-0.1));

        assertEquals(e.getMessage(), ACOUSTIC_MESSAGE);
    }

    @Test
    void verify_validateAcousticness_throwsExceptionWhenOnUpperBound()
    {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->
                spotifyRequestParamValidationService.validateAcousticness(1.1));

        assertEquals(e.getMessage(), ACOUSTIC_MESSAGE);
    }

    @Test
    void verify_validateAcousticness_isSuccessfulOnValidParam()
    {
        spotifyRequestParamValidationService.validateAcousticness(0.45);
    }

    @Test
    void verify_validateDanceability_throwsExceptionOnNullParameter()
    {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->
                spotifyRequestParamValidationService.validateDanceability(null));

        assertEquals(e.getMessage(), DANCEABLITY_MESSAGE);
    }

    @Test
    void verify_validateDanceability_throwsExceptionWhenOnLowerBound()
    {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->
                spotifyRequestParamValidationService.validateDanceability(-0.1));

        assertEquals(e.getMessage(), DANCEABLITY_MESSAGE);
    }

    @Test
    void verify_validateDanceability_throwsExceptionWhenOnUpperBound()
    {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->
                spotifyRequestParamValidationService.validateDanceability(1.1));

        assertEquals(e.getMessage(), DANCEABLITY_MESSAGE);
    }

    @Test
    void verify_validateDanceability_isSuccessfulOnValidParam()
    {
        spotifyRequestParamValidationService.validateDanceability(0.76);
    }

    @Test
    void verify_validateDurationMs_throwsExceptionOnNullParameter()
    {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->
                spotifyRequestParamValidationService.validateDurationMs(null));

        assertEquals(e.getMessage(), DURATION_MS_MESSAGE);
    }

    @Test
    void verify_validateDurationMs_throwsExceptionWhenOnLowerBound()
    {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->
                spotifyRequestParamValidationService.validateDurationMs(-1));

        assertEquals(e.getMessage(), DURATION_MS_MESSAGE);
    }

    @Test
    void verify_validateDurationMs_isSuccessfulOnValidParam()
    {
        spotifyRequestParamValidationService.validateDurationMs(12345678);
    }

    @Test
    void verify_validateEnergy_throwsExceptionOnNullParameter()
    {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->
                spotifyRequestParamValidationService.validateEnergy(null));

        assertEquals(e.getMessage(), ENERGY_MS_MESSAGE);
    }

    @Test
    void verify_validateEnergy_throwsExceptionWhenOnLowerBound()
    {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->
                spotifyRequestParamValidationService.validateEnergy(-0.1));

        assertEquals(e.getMessage(), ENERGY_MS_MESSAGE);
    }

    @Test
    void verify_validateEnergy_throwsExceptionWhenOnUpperBound()
    {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->
                spotifyRequestParamValidationService.validateEnergy(1.1));

        assertEquals(e.getMessage(), ENERGY_MS_MESSAGE);
    }

    @Test
    void verify_validateEnergy_isSuccessfulOnValidParam()
    {
        spotifyRequestParamValidationService.validateEnergy(0.29);
    }

    @Test
    void verify_validateInstrumentalness_throwsExceptionOnNullParameter()
    {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->
                spotifyRequestParamValidationService.validateInstrumentalness(null));

        assertEquals(e.getMessage(), INSTRUMENTALNESS_MS_MESSAGE);
    }

    @Test
    void verify_validateInstrumentalness_throwsExceptionWhenOnLowerBound()
    {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->
                spotifyRequestParamValidationService.validateInstrumentalness(-0.1));

        assertEquals(e.getMessage(), INSTRUMENTALNESS_MS_MESSAGE);
    }

    @Test
    void verify_validateInstrumentalness_throwsExceptionWhenOnUpperBound()
    {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->
                spotifyRequestParamValidationService.validateInstrumentalness(1.1));

        assertEquals(e.getMessage(), INSTRUMENTALNESS_MS_MESSAGE);
    }

    @Test
    void verify_validateInstrumentalness_isSuccessfulOnValidParam()
    {
        spotifyRequestParamValidationService.validateInstrumentalness(0.99);
    }

    @Test
    void verify_key_throwsExceptionOnNullParameter()
    {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->
                spotifyRequestParamValidationService.validateKey(null));

        assertEquals(e.getMessage(), KEY_MESSAGE);
    }

    @Test
    void verify_validateKey_isSuccessfulOnValidParam()
    {
        spotifyRequestParamValidationService.validateKey(14);
    }

    @Test
    void verify_validateLiveness_throwsExceptionOnNullParameter()
    {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->
                spotifyRequestParamValidationService.validateLiveness(null));

        assertEquals(e.getMessage(), LIVENESS_MESSAGE);
    }

    @Test
    void verify_validateLiveness_throwsExceptionWhenOnLowerBound()
    {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->
                spotifyRequestParamValidationService.validateLiveness(-0.1));

        assertEquals(e.getMessage(), LIVENESS_MESSAGE);
    }

    @Test
    void verify_validateLiveness_throwsExceptionWhenOnUpperBound()
    {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->
                spotifyRequestParamValidationService.validateLiveness(1.1));

        assertEquals(e.getMessage(), LIVENESS_MESSAGE);
    }

    @Test
    void verify_validateLiveness_isSuccessfulOnValidParam()
    {
        spotifyRequestParamValidationService.validateLiveness(0.81);
    }

    @Test
    void verify_validateLoudness_throwsExceptionOnNullParameter()
    {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->
                spotifyRequestParamValidationService.validateLoudness(null));

        assertEquals(e.getMessage(), LOUDNESS_MESSAGE);
    }

    @Test
    void verify_validateLoudness_throwsExceptionWhenOnLowerBound()
    {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->
                spotifyRequestParamValidationService.validateLoudness(-0.1));

        assertEquals(e.getMessage(), LOUDNESS_MESSAGE);
    }

    @Test
    void verify_validateLoudness_isSuccessfulOnValidParam()
    {
        spotifyRequestParamValidationService.validateLoudness(1243554.8);
    }

    @Test
    void verify_validateModality_throwsExceptionOnNullParameter()
    {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->
                spotifyRequestParamValidationService.validateModality(null));

        assertEquals(e.getMessage(), MODALITY_MESSAGE);
    }

    @Test
    void verify_validateModality_throwsExceptionWhenOnLowerBound()
    {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->
                spotifyRequestParamValidationService.validateModality(-1));

        assertEquals(e.getMessage(), MODALITY_MESSAGE);
    }

    @Test
    void verify_validateModality_isSuccessfulOnValidParam_1()
    {
        spotifyRequestParamValidationService.validateModality(1);
    }

    @Test
    void verify_validateModality_isSuccessfulOnValidParam_0()
    {
        spotifyRequestParamValidationService.validateModality(0);
    }

    @Test
    void verify_validatePopularity_throwsExceptionOnNullParameter()
    {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->
                spotifyRequestParamValidationService.validatePopularity(null));

        assertEquals(e.getMessage(), POPULARITY_MESSAGE);
    }

    @Test
    void verify_validatePopularity_throwsExceptionWhenOnLowerBound()
    {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->
                spotifyRequestParamValidationService.validatePopularity(-1));

        assertEquals(e.getMessage(), POPULARITY_MESSAGE);
    }

    @Test
    void verify_validatePopularity_throwsExceptionWhenOnUpperBound()
    {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->
                spotifyRequestParamValidationService.validatePopularity(101));

        assertEquals(e.getMessage(), POPULARITY_MESSAGE);
    }

    @Test
    void verify_validatePopularity_isSuccessfulOnValidParam()
    {
        spotifyRequestParamValidationService.validatePopularity(50);
    }

    @Test
    void verify_validateSpeechiness_throwsExceptionOnNullParameter()
    {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->
                spotifyRequestParamValidationService.validateSpeechiness(null));

        assertEquals(e.getMessage(), SPEECHINESS_MESSAGE);
    }

    @Test
    void verify_validateSpeechiness_throwsExceptionWhenOnLowerBound()
    {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->
                spotifyRequestParamValidationService.validateSpeechiness(-0.1));

        assertEquals(e.getMessage(), SPEECHINESS_MESSAGE);
    }

    @Test
    void verify_validateSpeechiness_throwsExceptionWhenOnUpperBound()
    {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->
                spotifyRequestParamValidationService.validateSpeechiness(1.1));

        assertEquals(e.getMessage(), SPEECHINESS_MESSAGE);
    }

    @Test
    void verify_validateSpeechiness_isSuccessfulOnValidParam()
    {
        spotifyRequestParamValidationService.validateSpeechiness(0.1);
    }

    @Test
    void verify_validateTempo_throwsExceptionOnNullParameter()
    {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->
                spotifyRequestParamValidationService.validateTempo(null));

        assertEquals(e.getMessage(), TEMPO_MESSAGE);
    }

    @Test
    void verify_validateTempo_throwsExceptionWhenOnLowerBound()
    {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->
                spotifyRequestParamValidationService.validateTempo(-0.1));

        assertEquals(e.getMessage(), TEMPO_MESSAGE);
    }

    @Test
    void verify_validateTempo_isSuccessfulOnValidParam()
    {
        spotifyRequestParamValidationService.validateTempo(200.0);
    }

    @Test
    void verify_validateTimeSignature_throwsExceptionOnNullParameter()
    {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->
                spotifyRequestParamValidationService.validateTimeSignature(null));

        assertEquals(e.getMessage(), TIME_SIGNATURE_MESSAGE);
    }

    @Test
    void verify_validateTimeSignature_throwsExceptionWhenOnLowerBound()
    {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->
                spotifyRequestParamValidationService.validateTimeSignature(-1));

        assertEquals(e.getMessage(), TIME_SIGNATURE_MESSAGE);
    }

    @Test
    void verify_validateTimeSignature_isSuccessfulOnValidParam()
    {
        spotifyRequestParamValidationService.validateTimeSignature(201);
    }

    @Test
    void verify_validateValence_throwsExceptionOnNullParameter()
    {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->
                spotifyRequestParamValidationService.validateValence(null));

        assertEquals(e.getMessage(), VALENCE_MESSAGE);
    }

    @Test
    void verify_validateValence_throwsExceptionWhenOnLowerBound()
    {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->
                spotifyRequestParamValidationService.validateValence(-0.1));

        assertEquals(e.getMessage(), VALENCE_MESSAGE);
    }

    @Test
    void verify_validateValence_throwsExceptionWhenOnUpperBound()
    {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->
                spotifyRequestParamValidationService.validateValence(1.1));

        assertEquals(e.getMessage(), VALENCE_MESSAGE);
    }

    @Test
    void verify_validateValence_isSuccessfulOnValidParam()
    {
        spotifyRequestParamValidationService.validateValence(0.1);
    }

    @Test
    void verify_validateList_throwsExceptionOnNullParameter()
    {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->
                spotifyRequestParamValidationService.validateList(null));

        assertEquals(e.getMessage(), LIST_MESSAGE);
    }

    @Test
    void verify_validateList_throwsExceptionOnEmptyParameter()
    {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->
                spotifyRequestParamValidationService.validateList(new ArrayList<>()));

        assertEquals(e.getMessage(), LIST_MESSAGE);
    }

    @Test
    void verify_validateList_isSuccessfulOnValidParam()
    {
        List<String> nonEmptyList = new ArrayList<>();
        nonEmptyList.add("element");
        spotifyRequestParamValidationService.validateList(nonEmptyList);
    }

    @Test
    void verify_validateLimit50_throwsExceptionOnNullParameter()
    {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->
                spotifyRequestParamValidationService.validateLimit50(null));

        assertEquals(e.getMessage(), LIMIT_50_MESSAGE);
    }

    @Test
    void verify_validateLimit50_throwsExceptionWhenOnLowerBound()
    {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->
                spotifyRequestParamValidationService.validateLimit50(-1));

        assertEquals(e.getMessage(), LIMIT_50_MESSAGE);
    }

    @Test
    void verify_validateLimit50_throwsExceptionWhenOnUpperBound()
    {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->
                spotifyRequestParamValidationService.validateLimit50(51));

        assertEquals(e.getMessage(), LIMIT_50_MESSAGE);
    }

    @Test
    void verify_validateLimit50_isSuccessfulOnValidParam()
    {
        spotifyRequestParamValidationService.validateLimit50(3);
    }

    @Test
    void verify_validateLimit100_throwsExceptionOnNullParameter()
    {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->
                spotifyRequestParamValidationService.validateLimit100(null));

        assertEquals(e.getMessage(), LIMIT_100_MESSAGE);
    }

    @Test
    void verify_validateLimit100_throwsExceptionWhenOnLowerBound()
    {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->
                spotifyRequestParamValidationService.validateLimit100(-1));

        assertEquals(e.getMessage(), LIMIT_100_MESSAGE);
    }

    @Test
    void verify_validateLimit100_throwsExceptionWhenOnUpperBound()
    {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->
                spotifyRequestParamValidationService.validateLimit100(101));

        assertEquals(e.getMessage(), LIMIT_100_MESSAGE);
    }

    @Test
    void verify_validateLimit100_isSuccessfulOnValidParam()
    {
        spotifyRequestParamValidationService.validateLimit100(83);
    }

    @Test
    void verify_validateOffset_throwsExceptionOnNullParameter()
    {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->
                spotifyRequestParamValidationService.validateOffset(null));

        assertEquals(e.getMessage(), OFFSET_MESSAGE);
    }

    @Test
    void verify_validateOffset_throwsExceptionWhenOnLowerBound()
    {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->
                spotifyRequestParamValidationService.validateOffset(-1));

        assertEquals(e.getMessage(), OFFSET_MESSAGE);
    }

    @Test
    void verify_validateOffset_isSuccessfulOnValidParam()
    {
        spotifyRequestParamValidationService.validateOffset(2501);
    }

    @Test
    void verify_validateUserIds_throwsExceptionOnNullParameter()
    {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->
                spotifyRequestParamValidationService.validateUserIds(null));

        assertEquals(e.getMessage(), USER_IDS_NULL_MESSAGE);
    }

    @Test
    void verify_validateUserIds_throwsExceptionWhenListIsTooLong()
    {
        List<String> userIds = new ArrayList<>();
        userIds.add("1");
        userIds.add("2");
        userIds.add("3");
        userIds.add("4");
        userIds.add("5");
        userIds.add("6");
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->
                spotifyRequestParamValidationService.validateUserIds(userIds));

        assertEquals(e.getMessage(), USER_IDS_TOO_LONG_MESSAGE);
    }

    @Test
    void verify_validateUserIds_isSuccessfulOnValidParam()
    {
        List<String> userIds = new ArrayList<>();
        userIds.add("1");
        spotifyRequestParamValidationService.validateUserIds(userIds);
    }

    @Test
    void verify_validateFollowIds_throwsExceptionOnNullParameter()
    {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->
                spotifyRequestParamValidationService.validateIds50(null));

        assertEquals(e.getMessage(), FOLLOW_IDS_EMPTY_MESSAGE);
    }

    @Test
    void verify_validateFollowIds_throwsExceptionOnEmptyList()
    {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->
                spotifyRequestParamValidationService.validateIds50(new ArrayList<>()));

        assertEquals(e.getMessage(), FOLLOW_IDS_EMPTY_MESSAGE);
    }

    @Test
    void verify_validateFollowIds_throwsExceptionWhenListIsTooLong()
    {
        List<String> ids = new ArrayList<>();
        for(int i = 0; i < 51; i++)
        {
            ids.add(String.valueOf(i));
        }
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->
                spotifyRequestParamValidationService.validateIds50(ids));

        assertEquals(e.getMessage(), FOLLOW_IDS_TOO_LONG_MESSAGE);
    }

    @Test
    void verify_validateFollowIds_isSuccessfulOnValidParam()
    {
        List<String> ids = new ArrayList<>();
        ids.add("1");
        spotifyRequestParamValidationService.validateIds50(ids);
    }

    @Test
    void verify_validatePlaylistUris_throwsExceptionOnNullParameter()
    {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->
                spotifyRequestParamValidationService.validatePlaylistUris(null));

        assertEquals(e.getMessage(), PLAYLIST_URIS_EMPTY_MESSAGE);
    }

    @Test
    void verify_validatePlaylistUris_throwsExceptionOnEmptyList()
    {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->
                spotifyRequestParamValidationService.validatePlaylistUris(new ArrayList<>()));

        assertEquals(e.getMessage(), PLAYLIST_URIS_EMPTY_MESSAGE);
    }

    @Test
    void verify_validatePlaylistUris_throwsExceptionWhenListIsTooLong()
    {
        List<String> ids = new ArrayList<>();
        for(int i = 0; i < 101; i++)
        {
            ids.add(String.valueOf(i));
        }
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->
                spotifyRequestParamValidationService.validatePlaylistUris(ids));

        assertEquals(e.getMessage(), PLAYLIST_URIS_TOO_LONG_MESSAGE);
    }

    @Test
    void verify_validatePlaylistUris_isSuccessfulOnValidParam()
    {
        List<String> ids = new ArrayList<>();
        ids.add("1");
        spotifyRequestParamValidationService.validatePlaylistUris(ids);
    }

    @Test
    void verify_validateTrackUris_throwsExceptionOnNullParameter()
    {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->
                spotifyRequestParamValidationService.validateTrackUris(null));

        assertEquals(e.getMessage(), TRACK_URIS_EMPTY_MESSAGE);
    }

    @Test
    void verify_validateTrackUris_throwsExceptionOnEmptyList()
    {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->
                spotifyRequestParamValidationService.validateTrackUris(new ArrayList<>()));

        assertEquals(e.getMessage(), TRACK_URIS_EMPTY_MESSAGE);
    }

    @Test
    void verify_validateTrackUris_throwsExceptionWhenListIsTooLong()
    {
        List<String> ids = new ArrayList<>();
        for(int i = 0; i < 101; i++)
        {
            ids.add(String.valueOf(i));
        }
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->
                spotifyRequestParamValidationService.validateTrackUris(ids));

        assertEquals(e.getMessage(), TRACK_URIS_TOO_LONG_MESSAGE);
    }

    @Test
    void verify_validateTrackUris_isSuccessfulOnValidParam()
    {
        List<String> ids = new ArrayList<>();
        ids.add("1");
        spotifyRequestParamValidationService.validateTrackUris(ids);
    }

    @Test
    void verify_validateVolume_throwsExceptionOnNullParameter()
    {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->
                spotifyRequestParamValidationService.validateVolume(null));

        assertEquals(e.getMessage(), VOLUME_INVALID_MESSAGE);
    }

    @Test
    void verify_validateVolume_throwsExceptionOnEmptyList()
    {
        Integer volume = -2345;
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->
                spotifyRequestParamValidationService.validateVolume(volume));

        assertEquals(e.getMessage(), VOLUME_INVALID_MESSAGE);
    }

    @Test
    void verify_validateVolume_throwsExceptionWhenListIsTooLong()
    {
        Integer volume = 2345;
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->
                spotifyRequestParamValidationService.validateVolume(volume));

        assertEquals(e.getMessage(), VOLUME_INVALID_MESSAGE);
    }

    @Test
    void verify_validateVolume_isSuccessfulOnValidParam()
    {
        Integer volume = 2;
        spotifyRequestParamValidationService.validateVolume(volume);
    }
}