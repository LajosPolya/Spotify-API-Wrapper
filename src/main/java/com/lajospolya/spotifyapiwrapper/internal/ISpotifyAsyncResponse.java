package com.lajospolya.spotifyapiwrapper.internal;

import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;

public interface ISpotifyAsyncResponse<AsyncContainer, T>
{
    ISpotifyAsyncResponse<AsyncContainer, T> thenAccept(Consumer<? super T> action);

    T get() throws ExecutionException, InterruptedException;
}
