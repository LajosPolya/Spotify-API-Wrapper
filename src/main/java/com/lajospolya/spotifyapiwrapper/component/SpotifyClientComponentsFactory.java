package com.lajospolya.spotifyapiwrapper.component;

public class SpotifyClientComponentsFactory
{
    private static final String JAVA_11_HTTP_CLIENT_QUALIFIER = "java.net.http.HttpClient";
    private static final boolean useJava11;

    static
    {
        useJava11 = doesJava11HttpClientExists();
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

    public static  ISpotifyClient spotifyClient()
    {
        if (useJava11)
        {
            return new Java11HttpClient();
        }
        return new OkHttp4Client();
    }

    public static ISpotifyRequestBuilder spotifyRequestBuilder(String uri)
    {
        if (useJava11)
        {
            return new Java11RequestBuilder(uri);
        }
        return new OkHttp4RequestBuilder(uri);
    }
}
