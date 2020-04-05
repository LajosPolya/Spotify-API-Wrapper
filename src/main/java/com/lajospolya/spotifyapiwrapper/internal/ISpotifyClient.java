package com.lajospolya.spotifyapiwrapper.internal;

import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public interface ISpotifyClient<T>
{
    HttpResponse<String> send(ISpotifyRequest<T> request);

    CompletableFuture<?> sendAsync(ISpotifyRequest<T> request);
}
