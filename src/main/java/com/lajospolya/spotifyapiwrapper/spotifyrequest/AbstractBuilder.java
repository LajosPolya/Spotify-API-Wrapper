package com.lajospolya.spotifyapiwrapper.spotifyrequest;

public abstract class AbstractBuilder
{
    String ILLEGAL_ARGUMENT_EXCEPTION_MSG = "Builder Constructor parameters cannot be null";

    void validateParametersNotNull(Object ... params) throws IllegalArgumentException
    {
        for(Object param : params)
        {
            if(param == null)
            {
                throw new IllegalArgumentException(ILLEGAL_ARGUMENT_EXCEPTION_MSG);
            }
        }
    }
}
