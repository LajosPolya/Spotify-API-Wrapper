package com.lajospolya.spotifyapiwrapper.internal;

import com.google.gson.Gson;
import com.lajospolya.spotifyapiwrapper.response.SpotifyErrorContainer;

import java.lang.reflect.Type;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class Java11HttpResponse<T> implements ISpotifyResponse<T>
{
    private Gson gson;
    private HttpResponse<String> response;

    public Java11HttpResponse(HttpResponse<String> response)
    {
        this.gson = new Gson();
        this.response = response;
    }

    @Override
    public T body(Type typeOfBody)
    {
        String body = response.body();
        if(isStringType(typeOfBody))
        {
            return (T) body;
        }
        return gson.fromJson(body, typeOfBody);
    }

    @Override
    public SpotifyErrorContainer error()
    {
        return gson.fromJson(response.body(), SpotifyErrorContainer.class);
    }

    private Boolean isStringType(Type typeOfReturnValue)
    {
        return String.class.getTypeName().equals(typeOfReturnValue.getTypeName());
    }

    @Override
    public Integer statusCode()
    {
        return response.statusCode();
    }

    @Override
    public Map<String, List<String>> headers()
    {
        return response.headers().map();
    }


}
