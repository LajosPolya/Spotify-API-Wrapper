package com.lajospolya.spotifyapiwrapper.spotifyrequest;

public abstract class AbstractBuilder
{
    String NULL_CONSTRUCTOR_PARAM_EXCEPTION_MSG = "Builder Constructor parameters cannot be null";
    String ILLEGAL_LIMIT_EXCEPTION_MSG = "Limit must be between 1 and 50 inclusive";
    String ILLEGAL_OFFSET_EXCEPTION_MSG = "Offset cannot be negative";

    static final String IDS_QUERY_PARAM = "ids";
    static final String MARKET_QUERY_PARAM = "market";
    static final String LIMIT_QUERY_PARAM = "limit";
    static final String OFFSET_QUERY_PARAM = "offset";

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
