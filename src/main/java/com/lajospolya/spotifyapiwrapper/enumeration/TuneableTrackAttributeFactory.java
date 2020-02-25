package com.lajospolya.spotifyapiwrapper.enumeration;

import com.lajospolya.spotifyapiwrapper.spotifyrequest.service.ISpotifyRequestParamValidationService;
import com.lajospolya.spotifyapiwrapper.spotifyrequest.service.SpotifyRequestParamValidationService;

public class TuneableTrackAttributeFactory
{
    public static Acousticness acousticness = new Acousticness();
    public static Danceability danceability = new Danceability();
    public static DurationMs durationMs = new DurationMs();
    public static Energy energy = new Energy();
    public static Instrumentalness instrumentalness = new Instrumentalness();
    public static Key key = new Key();
    public static Liveness liveness = new Liveness();
    public static Loudness loudness = new Loudness();
    public static Modality modality = new Modality();
    public static Popularity popularity = new Popularity();
    public static Speechiness speechiness = new Speechiness();
    public static Tempo tempo = new Tempo();
    public static TimeSignature timeSignature = new TimeSignature();
    public static Valence valence = new Valence();

    private static ISpotifyRequestParamValidationService spotifyRequestParamValidationService = new SpotifyRequestParamValidationService();
    private TuneableTrackAttributeFactory() {}

    public static abstract class AbstractTuneableTrackAttribute<T>
    {
        public abstract void validate(T t) throws IllegalArgumentException;
        public abstract String name();
    }

    private static class Acousticness extends AbstractTuneableTrackAttribute<Double>
    {
        @Override
        public void validate(Double acousticness) throws IllegalArgumentException
        {
            spotifyRequestParamValidationService.validateAcousticness(acousticness);
        }

        @Override
        public String name()
        {
            return "acousticness";
        }
    }

    private static class Danceability extends AbstractTuneableTrackAttribute<Double>
    {
        @Override
        public void validate(Double danceability) throws IllegalArgumentException
        {
            spotifyRequestParamValidationService.validateDanceability(danceability);
        }

        @Override
        public String name()
        {
            return "danceability";
        }
    }

    private static class DurationMs extends AbstractTuneableTrackAttribute<Integer>
    {
        @Override
        public void validate(Integer durationMs) throws IllegalArgumentException
        {
            spotifyRequestParamValidationService.validateDurationMs(durationMs);
        }

        @Override
        public String name()
        {
            return "duration_ms";
        }
    }

    private static class Energy extends AbstractTuneableTrackAttribute<Double>
    {
        @Override
        public void validate(Double energy) throws IllegalArgumentException
        {
            spotifyRequestParamValidationService.validateEnergy(energy);
        }

        @Override
        public String name()
        {
            return "energy";
        }
    }

    private static class Instrumentalness extends AbstractTuneableTrackAttribute<Double>
    {
        @Override
        public void validate(Double instrumentalness) throws IllegalArgumentException
        {
            spotifyRequestParamValidationService.validateInstrumentalness(instrumentalness);
        }

        @Override
        public String name()
        {
            return "instrumentalness";
        }
    }

    private static class Key extends AbstractTuneableTrackAttribute<Integer>
    {
        @Override
        public void validate(Integer key) throws IllegalArgumentException
        {
            spotifyRequestParamValidationService.validateKey(key);
        }

        @Override
        public String name()
        {
            return "key";
        }
    }

    private static class Liveness extends AbstractTuneableTrackAttribute<Double>
    {
        @Override
        public void validate(Double liveness) throws IllegalArgumentException
        {
            spotifyRequestParamValidationService.validateLiveness(liveness);
        }

        @Override
        public String name()
        {
            return "liveness";
        }
    }

    private static class Loudness extends AbstractTuneableTrackAttribute<Double>
    {
        @Override
        public void validate(Double loudness) throws IllegalArgumentException
        {
            spotifyRequestParamValidationService.validateLoudness(loudness);
        }

        @Override
        public String name()
        {
            return "loudness";
        }
    }

    private static class Modality extends AbstractTuneableTrackAttribute<Integer>
    {
        @Override
        public void validate(Integer modality) throws IllegalArgumentException
        {
            spotifyRequestParamValidationService.validateModality(modality);
        }

        @Override
        public String name()
        {
            return "modality";
        }
    }

    private static class Popularity extends AbstractTuneableTrackAttribute<Integer>
    {
        @Override
        public void validate(Integer popularity) throws IllegalArgumentException
        {
            spotifyRequestParamValidationService.validatePopularity(popularity);
        }

        @Override
        public String name()
        {
            return "popularity";
        }
    }

    private static class Speechiness extends AbstractTuneableTrackAttribute<Double>
    {
        @Override
        public void validate(Double speechiness) throws IllegalArgumentException
        {
            spotifyRequestParamValidationService.validateSpeechiness(speechiness);
        }

        @Override
        public String name()
        {
            return "speechiness";
        }
    }

    private static class Tempo extends AbstractTuneableTrackAttribute<Double>
    {
        @Override
        public void validate(Double tempo) throws IllegalArgumentException
        {
            spotifyRequestParamValidationService.validateTempo(tempo);
        }

        @Override
        public String name()
        {
            return "tempo";
        }
    }

    private static class TimeSignature extends AbstractTuneableTrackAttribute<Integer>
    {
        @Override
        public void validate(Integer timeSignature) throws IllegalArgumentException
        {
            spotifyRequestParamValidationService.validateTimeSignature(timeSignature);
        }

        @Override
        public String name()
        {
            return "time_signature";
        }
    }

    private static class Valence extends AbstractTuneableTrackAttribute<Double>
    {
        @Override
        public void validate(Double valence) throws IllegalArgumentException
        {
            spotifyRequestParamValidationService.validateValence(valence);
        }

        @Override
        public String name()
        {
            return "valence";
        }
    }
}
