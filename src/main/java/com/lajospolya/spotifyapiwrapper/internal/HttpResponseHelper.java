package com.lajospolya.spotifyapiwrapper.internal;

import com.google.gson.Gson;
import com.lajospolya.spotifyapiwrapper.response.CacheableResponse;

import java.lang.reflect.Type;
import java.net.http.HttpResponse;
import java.util.List;

public class HttpResponseHelper
{
    private static final String ETAG_HEADER = "etag";

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
        T body = serializeResponseBody(response.body(), type);

        /*
         * when a 304 is returned with an empty body the serialized body becomes null
         * so we don't need to handled caching separately
         */
        setCachableValuesFromHeadersIfCachable(body,response);

        return body;
    }

    private <T> T serializeResponseBody(String body, Type type)
    {
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

    public <T> void setCachableValuesFromHeadersIfCachable(T body, HttpResponse<String> response)
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
}
