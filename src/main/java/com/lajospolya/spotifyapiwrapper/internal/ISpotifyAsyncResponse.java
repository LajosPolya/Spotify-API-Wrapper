package com.lajospolya.spotifyapiwrapper.internal;

import java.lang.reflect.Type;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;
import java.util.function.Function;

public interface ISpotifyAsyncResponse<AsyncContainer, T>
{
    ISpotifyAsyncResponse<AsyncContainer, T> thenAccept(Consumer<? super T> action);

    T get() throws ExecutionException, InterruptedException;

    void validateResponseAsync(Function<Integer, Boolean> validateByStatusCode, Type typeOfBody);
}
