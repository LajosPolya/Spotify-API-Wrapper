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
    private Type type;
    private T body;

    public Java11HttpResponse(HttpResponse<String> response, Type typeOfResponse)
    {
        this.gson = new Gson();
        this.response = response;
        this.type = typeOfResponse;
        serializeBody();
    }

    private void serializeBody()
    {
        String body = response.body();
        if(isStringType(type))
        {
            this.body =  (T) body;
        }
        this.body =  gson.fromJson(body, type);
    }

    @Override
    public T body(Type typeOfBody)
    {
        return this.body;
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
