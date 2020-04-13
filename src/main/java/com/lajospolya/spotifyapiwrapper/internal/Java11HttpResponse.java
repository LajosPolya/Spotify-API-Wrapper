package com.lajospolya.spotifyapiwrapper.internal;

import com.google.gson.Gson;
import com.lajospolya.spotifyapiwrapper.response.CacheableResponse;
import com.lajospolya.spotifyapiwrapper.response.SpotifyErrorContainer;

import java.lang.reflect.Type;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class Java11HttpResponse<T> implements ISpotifyResponse<T>
{
    private static final String ETAG_HEADER = "etag";

    private final HttpResponseHelper helper;
    private final Gson gson;

    private HttpResponse<String> response;
    private Type type;
    private T body;
    private SpotifyErrorContainer error;
    private boolean erroneous = false;

    public Java11HttpResponse(HttpResponse<String> response, Type typeOfResponse)
    {
        this.helper = new HttpResponseHelper();
        this.gson = new Gson();
        this.response = response;
        this.type = typeOfResponse;
        validateResponse();
    }

    private void validateResponse()
    {
        int statusCode = response.statusCode();
        if(helper.isClientErrorStatusCode(statusCode) || helper.isServerErrorStatusCode(statusCode))
        {
            serializeError();
            erroneous = true;
        }
        else
        {
            serializeBody();
            /*
             * when a 304 is returned with an empty body the serialized body becomes null
             * so we don't need to handled caching separately
             */
            setCachableValuesFromHeadersIfCachable(body);
        }
    }

    private void serializeError()
    {
        error = gson.fromJson(response.body(), SpotifyErrorContainer.class);
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

    private void setCachableValuesFromHeadersIfCachable(T body)
    {
        if(body instanceof CacheableResponse)
        {
            // Use the Optional interface here
            List<String> etag = response.headers().map().get(ETAG_HEADER);
            if(etag != null && !etag.isEmpty())
            {
                ((CacheableResponse) body).setEtag(etag.get(0));
            }
        }
    }

    @Override
    public T body()
    {
        return this.body;
    }

    @Override
    public SpotifyErrorContainer error()
    {
        return error;
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
