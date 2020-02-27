package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import com.lajospolya.spotifyapiwrapper.spotifyrequest.service.ISpotifyRequestParamValidationService;
import com.lajospolya.spotifyapiwrapper.spotifyrequest.service.SpotifyRequestParamValidationService;

public abstract class AbstractBuilder
{
    String NULL_CONSTRUCTOR_PARAM_EXCEPTION_MSG = "Builder Constructor parameters cannot be null";
    String ILLEGAL_LIMIT_EXCEPTION_MSG = "Limit must be between 1 and 50 inclusive";
    String ILLEGAL_OFFSET_EXCEPTION_MSG = "Offset cannot be negative";

    static final String IDS_QUERY_PARAM = "ids";
    static final String MARKET_QUERY_PARAM = "market";
    static final String COUNTRY_QUERY_PARAM = "country";
    static final String LOCALE_QUERY_PARAM = "locale";
    static final String LIMIT_QUERY_PARAM = "limit";
    static final String OFFSET_QUERY_PARAM = "offset";
    static final String TIMESTAMP_QUERY_PARAM = "timestamp";
    static final String SEED_ARTISTS_QUERY_PARAM = "seed_artists";
    static final String SEED_TRACKS_QUERY_PARAM = "seed_tracks";
    static final String SEED_GENRES_QUERY_PARAM = "seed_genres";
    static final String SEARCH_ALBUM_TYPE = "type";
    static final String MIN_PARAM_PREFIX = "min_";
    static final String MAX_PARAM_PREFIX = "max_";
    static final String TARGET_PARAM_PREFIX = "target_";
    static final String QUERY = "q";
    static final String INCLUDE_EXTERNAL = "include_external";

    ISpotifyRequestParamValidationService spotifyRequestParamValidationService;

    AbstractBuilder()
    {
        spotifyRequestParamValidationService = new SpotifyRequestParamValidationService();
    }

    void validateParametersNotNull(Object ... params) throws IllegalArgumentException
    {
        for(Object param : params)
        {
            if(param == null)
            {
                throw new IllegalArgumentException(NULL_CONSTRUCTOR_PARAM_EXCEPTION_MSG);
            }
        }
    }
}
