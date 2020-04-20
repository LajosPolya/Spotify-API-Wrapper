package com.lajospolya.spotifyapiwrapper.component;

import com.lajospolya.spotifyapiwrapper.component.service.HttpResponseHelper;
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
            erroneous = true;
            error = helper.serializeBody(response.body(), response.headers().map(), SpotifyErrorContainer.class);
        }
        else
        {
            body = helper.serializeBody(response.body(), response.headers().map(), type);
        }
    }

    @Override
    public T body() throws SpotifyResponseException
    {
        if(erroneous)
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
