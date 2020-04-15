package com.lajospolya.spotifyapiwrapper.internal;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;
import java.util.function.Function;

public class CompletableFutureAsyncResponse<T> implements ISpotifyAsyncResponse<CompletableFuture<?>, T>
{
    private CompletableFuture<T> asyncContainer;

    public CompletableFutureAsyncResponse(CompletableFuture<T> asyncContainer)
    {
        this.asyncContainer = asyncContainer;
    }

    @Override
    public <V> CompletableFutureAsyncResponse<T> thenApply(Function<? super T, ? extends V> fn)
    {
        asyncContainer = (CompletableFuture<T>) asyncContainer.thenApply(fn);
        return this;
    }

    @Override
    public CompletableFuture<Void> thenAccept(Consumer<? super T> action)
    {
        return asyncContainer.thenAccept(action);
    }

    @Override
    public T get() throws ExecutionException, InterruptedException
    {
        return asyncContainer.get();
    }
}
