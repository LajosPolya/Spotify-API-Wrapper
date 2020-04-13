package com.lajospolya.spotifyapiwrapper.internal;

public class HttpResponseHelper
{
    public Boolean isClientErrorStatusCode(int statusCode)
    {
        return statusCode / 100 == 4;
    }

    public Boolean isServerErrorStatusCode(int statusCode)
    {
        return statusCode / 100 == 5;
    }
}
