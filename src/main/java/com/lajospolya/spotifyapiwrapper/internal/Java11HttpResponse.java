package com.lajospolya.spotifyapiwrapper.internal;

import com.lajospolya.spotifyapiwrapper.response.SpotifyErrorContainer;
import com.lajospolya.spotifyapiwrapper.spotifyexception.SpotifyResponseException;

import java.lang.reflect.Type;
import java.net.http.HttpResponse;

public class Java11HttpResponse<T> implements ISpotifyResponse<T>
{
    private final HttpResponseHelper helper;

    private final HttpResponse<String> response;
    private final Type type;
    private T body;
    private SpotifyErrorContainer error;

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
        }
        else
        {
            body = helper.serializeBody(response, type);
        }
    }

    @Override
    public T body()
    {
        if(body == null)
        {
            throw new SpotifyResponseException("Response was not successful");
        }
        return body;
    }

    @Override
    public SpotifyErrorContainer error()
    {
        return error;
    }
}
