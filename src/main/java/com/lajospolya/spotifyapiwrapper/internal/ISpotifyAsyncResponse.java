package com.lajospolya.spotifyapiwrapper.internal;

import com.lajospolya.spotifyapiwrapper.response.SpotifyErrorContainer;

import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;

public interface ISpotifyAsyncResponse<AsyncContainer, T>
{
    void block() throws ExecutionException, InterruptedException;

    ISpotifyAsyncResponse<AsyncContainer, T> success(Consumer<T> func);

    ISpotifyAsyncResponse<AsyncContainer, T> error(Consumer<SpotifyErrorContainer> func);

    boolean isDone();
}
