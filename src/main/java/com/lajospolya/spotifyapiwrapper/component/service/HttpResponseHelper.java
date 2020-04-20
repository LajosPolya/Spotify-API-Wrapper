package com.lajospolya.spotifyapiwrapper.component.service;

import com.google.gson.Gson;
import com.lajospolya.spotifyapiwrapper.response.CacheableResponse;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

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

    public <T> T serializeBody(String responseBody, Map<String, List<String>> headers, Type type)
    {
        T body = serializeResponseBody(responseBody, type);

        /*
         * when a 304 is returned with an empty body the serialized body becomes null
         * so we don't need to handled caching separately
         */
        setCachableValuesFromHeadersIfCachable(body,headers);

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

    private <T> void setCachableValuesFromHeadersIfCachable(T body, Map<String, List<String>> headers)
    {
        if(body instanceof CacheableResponse)
        {
            List<String> etags = headers.get(ETAG_HEADER);
            if(etags != null && etags.size() > 0)
            {
                String etag = etags.get(0);
                if(etag != null)
                {
                    ((CacheableResponse) body).setEtag(etag);
                }
            }
        }
    }
}
