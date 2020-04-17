package com.lajospolya.spotifyapiwrapper.internal;

import com.lajospolya.spotifyapiwrapper.response.SpotifyErrorContainer;

import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;

public interface ISpotifyAsyncResponse<T>
{
    void block() throws ExecutionException, InterruptedException;

    ISpotifyAsyncResponse<T> success(Consumer<T> func);

    ISpotifyAsyncResponse<T> error(Consumer<SpotifyErrorContainer> func);

    boolean isDone();
}
