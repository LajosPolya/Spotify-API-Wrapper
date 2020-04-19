package com.lajospolya.spotifyapiwrapper.request;

import com.lajospolya.spotifyapiwrapper.internal.ISpotifyRequestBuilder;

public abstract class CacheableRequestBuilder<T> extends AbstractBuilder<T>
{
    String etag = null;

    public abstract CacheableRequestBuilder<T> etag(String etag);

    void addEtagHeader(ISpotifyRequestBuilder requestBuilder)
    {
        if(etag != null)
        {
            requestBuilder.etag(etag);
        }
    }
}
