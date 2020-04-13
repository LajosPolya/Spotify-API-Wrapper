package com.lajospolya.spotifyapiwrapper.internal;

import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.net.http.HttpResponse;

public class HttpResponseHelper
{
    private final Gson gson = new Gson();
    public Boolean isClientErrorStatusCode(int statusCode)
    {
        return statusCode / 100 == 4;
    }

    public Boolean isServerErrorStatusCode(int statusCode)
    {
        return statusCode / 100 == 5;
    }

    public <T> T serializeBody(HttpResponse<String> response, Type type)
    {
        String body = response.body();
        if(isStringType(type))
        {
            return (T) body;
        }
        return gson.fromJson(body, type);
    }

    private Boolean isStringType(Type typeOfReturnValue)
    {
        return String.class.getTypeName().equals(typeOfReturnValue.getTypeName());
    }
}
