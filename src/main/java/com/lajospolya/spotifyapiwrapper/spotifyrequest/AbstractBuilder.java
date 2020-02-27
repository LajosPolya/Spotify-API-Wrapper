package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import com.lajospolya.spotifyapiwrapper.spotifyrequest.service.ISpotifyRequestParamValidationService;
import com.lajospolya.spotifyapiwrapper.spotifyrequest.service.SpotifyRequestParamValidationService;

import java.nio.charset.StandardCharsets;

public abstract class AbstractBuilder
{
    String NULL_CONSTRUCTOR_PARAM_EXCEPTION_MSG = "Builder Constructor parameters cannot be null";

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
    static final String CODE_QUERY_PARAM = "code";
    static final String REDIRECT_URI_QUERY_PARAM = "redirect_uri";
    static final String SEARCH_ALBUM_TYPE = "type";
    static final String MIN_PARAM_PREFIX = "min_";
    static final String MAX_PARAM_PREFIX = "max_";
    static final String TARGET_PARAM_PREFIX = "target_";

    static final String CONTENT_TYPE_HEADER = "Content-Type";
    static final String URL_ENCODED_CONTENT_TYPE_HEADER_VALUE = "application/x-www-form-urlencoded";
    static final byte[] GRANT_TYPE_BODY_PARAMS = "grant_type=client_credentials".getBytes(StandardCharsets.UTF_8);

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
