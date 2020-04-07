package com.lajospolya.spotifyapiwrapper.internal;

import java.util.concurrent.CompletableFuture;

public interface ISpotifyClient
{
    ISpotifyResponse<?> send(ISpotifyRequest<?> request);

    CompletableFuture<?> sendAsync(ISpotifyRequest<?> request);
}
