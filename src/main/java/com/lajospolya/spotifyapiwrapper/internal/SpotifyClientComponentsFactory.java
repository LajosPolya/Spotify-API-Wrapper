package com.lajospolya.spotifyapiwrapper.internal;

import com.lajospolya.spotifyapiwrapper.request.ISpotifyRequestBuilder;
import com.lajospolya.spotifyapiwrapper.request.OkHttpRequestBuilder;
import com.lajospolya.spotifyapiwrapper.request.SpotifyRequestBuilder;

public class SpotifyClientComponentsFactory
{
    private static final String JAVA_11_HTTP_CLIENT_QUALIFIER = "java.net.http.HttpClient";
    public static ISpotifyRequestBuilder spotifyRequestBuilder(String uri)
    {
        if (doesJava11HttpClientExists())
        {
            return new SpotifyRequestBuilder(uri);
        }
        return new OkHttpRequestBuilder(uri);
    }

    public static ISpotifyRequestBuilder spotifyRequestBuilder(String uri, String pathVariable)
    {
        if (doesJava11HttpClientExists())
        {
            return new SpotifyRequestBuilder(uri, pathVariable);
        }
        return new OkHttpRequestBuilder(uri, pathVariable);
    }

    private static boolean doesJava11HttpClientExists()
    {
        try
        {
            Class.forName(JAVA_11_HTTP_CLIENT_QUALIFIER);
            return true;
        }
        catch (ClassNotFoundException e)
        {
            return false;
        }
    }

}
