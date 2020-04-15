package com.lajospolya.spotifyapiwrapper.internal;

import com.lajospolya.spotifyapiwrapper.response.SpotifyErrorContainer;

import java.lang.reflect.Type;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class Java11HttpResponse<T> implements ISpotifyResponse<T>
{
    private final HttpResponseHelper helper;

    private final HttpResponse<String> response;
    private final Type type;
    private T body;
    private SpotifyErrorContainer error;
    private boolean erroneous = false;

    public Java11HttpResponse(HttpResponse<String> response, Type typeOfResponse)
    {
        this.helper = new HttpResponseHelper();
        this.response = response;
        this.type = typeOfResponse;
        validateResponse();
    }

    private void validateResponse()
    {
        int statusCode = response.statusCode();
        if(helper.isClientErrorStatusCode(statusCode) || helper.isServerErrorStatusCode(statusCode))
        {
            error = helper.serializeBody(response, SpotifyErrorContainer.class);
            erroneous = true;
        }
        else
        {
            body = helper.serializeBody(response, type);
        }
    }

    @Override
    public T body()
    {
        return body;
    }

    @Override
    public SpotifyErrorContainer error()
    {
        return error;
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
