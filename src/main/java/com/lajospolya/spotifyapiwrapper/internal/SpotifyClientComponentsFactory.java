package com.lajospolya.spotifyapiwrapper.internal;

import com.lajospolya.spotifyapiwrapper.request.ISpotifyRequestBuilder;
import com.lajospolya.spotifyapiwrapper.request.OkHttpRequestBuilder;

public class SpotifyClientComponentsFactory
{
    public static ISpotifyRequestBuilder spotifyRequestBuilder(String uri)
    {
        return new OkHttpRequestBuilder(uri);
    }

    public static ISpotifyRequestBuilder spotifyRequestBuilder(String uri, String pathVariable)
    {
        return new OkHttpRequestBuilder(uri, pathVariable);
    }
}
