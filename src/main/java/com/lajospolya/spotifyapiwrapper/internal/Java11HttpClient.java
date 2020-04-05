package com.lajospolya.spotifyapiwrapper.internal;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class Java11HttpClient implements ISpotifyClient<HttpRequest>
{
    private HttpClient httpClient;

    public Java11HttpClient()
    {
        this.httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();
    }

    @Override
    public ISpotifyResponse<?> send(ISpotifyRequest<?> request)
    {
        try
        {
            return new Java11HttpResponse(httpClient.send((HttpRequest) request.get(), HttpResponse.BodyHandlers.ofString()));
        }
        catch (InterruptedException | IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public CompletableFuture<?> sendAsync(ISpotifyRequest<?> request)
    {
        return httpClient.sendAsync((HttpRequest) request.get(), HttpResponse.BodyHandlers.ofString());
    }
}