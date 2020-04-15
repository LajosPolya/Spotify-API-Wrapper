package com.lajospolya.spotifyapiwrapper.internal;

import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;
import java.util.function.Function;

public interface ISpotifyAsyncResponse<AsyncContainer, T>
{
    <V> ISpotifyAsyncResponse<AsyncContainer, T> thenApply(Function<? super T, ? extends V> fn);

    AsyncContainer thenAccept(Consumer<? super T> action);

    T get() throws ExecutionException, InterruptedException;
}
