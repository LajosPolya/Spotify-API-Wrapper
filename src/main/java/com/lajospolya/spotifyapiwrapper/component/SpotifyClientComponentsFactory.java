package com.lajospolya.spotifyapiwrapper.component;

public class SpotifyClientComponentsFactory
{
    private static final String JAVA_11_HTTP_CLIENT_QUALIFIER = "java.net.http.HttpClient";

    public static  ISpotifyClient spotifyClient()
    {
        if (doesJava11HttpClientExists())
        {
            return new Java11HttpClient();
        }
        return new OkHttp4Client();
    }

    public static ISpotifyRequestBuilder spotifyRequestBuilder(String uri)
    {
        if (doesJava11HttpClientExists())
        {
            return new Java11RequestBuilder(uri);
        }
        return new OkHttp4RequestBuilder(uri);
    }

    public static ISpotifyRequestBuilder spotifyRequestBuilder(String uri, String pathVariable)
    {
        if (doesJava11HttpClientExists())
        {
            return new Java11RequestBuilder(uri, pathVariable);
        }
        return new OkHttp4RequestBuilder(uri, pathVariable);
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
