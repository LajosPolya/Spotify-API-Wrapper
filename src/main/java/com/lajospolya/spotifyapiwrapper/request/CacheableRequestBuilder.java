package com.lajospolya.spotifyapiwrapper.request;

public abstract class CacheableRequestBuilder<T> extends AbstractBuilder<T>
{
    String etag = null;

    public abstract CacheableRequestBuilder<T> etag(String etag);

    void addEtagHeader(SpotifyRequestBuilder requestBuilder)
    {
        if(etag != null)
        {
            requestBuilder.etag(etag);
        }
    }
}
