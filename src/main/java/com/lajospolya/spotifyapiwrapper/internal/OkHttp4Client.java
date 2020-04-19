package com.lajospolya.spotifyapiwrapper.internal;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.lang.reflect.Type;

public class OkHttp4Client implements ISpotifyClient
{
    private OkHttpClient client = new OkHttpClient.Builder().build();
    @Override
    public <T> ISpotifyResponse<T> send(ISpotifyRequest<T> request, Type typeOfResponse)
    {
        Response response = null;
        try
        {
            response = client.newCall((Request) request.get()).execute();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return new OkHttpResponse<>(response, typeOfResponse);
    }

    @Override
    public <T> ISpotifyAsyncResponse<T> sendAsync(ISpotifyRequest<T> request, Type typeOfResponse)
    {
        return new OkHttpAsyncResponse<>(client.newCall((Request) request.get()), typeOfResponse);
    }
}
