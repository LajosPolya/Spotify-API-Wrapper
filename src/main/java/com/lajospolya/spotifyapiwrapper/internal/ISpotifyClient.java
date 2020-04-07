package com.lajospolya.spotifyapiwrapper.internal;

import java.util.concurrent.CompletableFuture;

public interface ISpotifyClient<T>
{
    ISpotifyResponse<?> send(ISpotifyRequest<?> request);

    CompletableFuture<?> sendAsync(ISpotifyRequest<?> request);
}
