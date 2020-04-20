package com.lajospolya.spotifyapiwrapper.component;

import com.lajospolya.spotifyapiwrapper.response.SpotifyErrorContainer;
import com.lajospolya.spotifyapiwrapper.spotifyexception.SpotifyResponseException;
import okhttp3.Response;

import java.io.IOException;
import java.lang.reflect.Type;

public class OkHttp4Response<T> implements ISpotifyResponse<T>
{
    private final HttpResponseHelper helper;

    private final Response response;
    private final Type type;
    private T body;
    private SpotifyErrorContainer error;
    private boolean erroneous = false;

    public OkHttp4Response(Response response, Type typeOfResponse)
    {
        this.helper = new HttpResponseHelper();
        this.response = response;
        this.type = typeOfResponse;
        try
        {
            validateResponse();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void validateResponse() throws IOException
    {
        int statusCode = response.code();
        if(helper.isClientErrorStatusCode(statusCode) || helper.isServerErrorStatusCode(statusCode))
        {
            erroneous = true;
            error = helper.serializeBody(response.body().string(), response.headers().toMultimap(), SpotifyErrorContainer.class);
        }
        else
        {
            body = helper.serializeBody(response.body().string(), response.headers().toMultimap(), type);
        }
    }

    @Override
    public T body()
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
