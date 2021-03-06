package com.lajospolya.spotifyapiwrapper.component;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Java11HttpClient implements ISpotifyClient
{
    private final HttpClient httpClient;

    public Java11HttpClient()
    {
        this.httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();
    }

    @Override
    public <T> ISpotifyResponse<T> send(ISpotifyRequest<?> request, Type typeOfResponse)
    {
        try
        {
            return new Java11HttpResponse<>(httpClient.send((HttpRequest) request.get(), HttpResponse.BodyHandlers.ofString()), typeOfResponse);
        }
        catch (InterruptedException | IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public <T> ISpotifyAsyncResponse<T> sendAsync(ISpotifyRequest<?> request, Type typeOfResponse)
    {
        return new CompletableFutureAsyncResponse<>(
                httpClient.sendAsync((HttpRequest) request.get(), HttpResponse.BodyHandlers.ofString()), typeOfResponse);
    }
}
