package com.lajospolya.spotifyapiwrapper.internal;

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
            /*
             * when a 304 is returned with an empty body the serialized body becomes null
             * so we don't need to handled caching separately
             */
            helper.setCachableValuesFromHeadersIfCachable(body, response);
        }
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
