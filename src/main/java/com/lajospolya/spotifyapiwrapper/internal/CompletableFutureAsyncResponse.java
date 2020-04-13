package com.lajospolya.spotifyapiwrapper.internal;

import java.lang.reflect.Type;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;

public class CompletableFutureAsyncResponse<T> implements ISpotifyAsyncResponse<CompletableFuture<?>, T>
{
    private final HttpResponseHelper helper;
    private final CompletableFuture<HttpResponse<String>> asyncContainer;
    private final Type type;
    private CompletableFuture<T> serializedValue;
    private Boolean isValid = null;

    public CompletableFutureAsyncResponse(CompletableFuture<HttpResponse<String>> asyncContainer, Type type)
    {
        this.helper = new HttpResponseHelper();
        this.asyncContainer = asyncContainer;
        this.type = type;
    }

    @Override
    public CompletableFutureAsyncResponse<T> thenAccept(Consumer<? super T> action)
    {
        asyncContainer.thenAccept((response) -> serializedValue.thenAccept(action));
        return this;
    }

    @Override
    public T get() throws ExecutionException, InterruptedException
    {

        asyncContainer.get();
        return serializedValue.get();
    }

    @Override
    public void validateResponseAsync()
    {
        serializedValue = asyncContainer.thenApply((response) -> {
            int statusCode = response.statusCode();

            if(!(helper.isClientErrorStatusCode(statusCode) || helper.isServerErrorStatusCode(statusCode)))
            {
                return helper.serializeBody(response, type);
            }
            else
            {
                return null;
            }
        });
    }

    private Boolean isStringType(Type typeOfReturnValue)
    {
        return String.class.getTypeName().equals(typeOfReturnValue.getTypeName());
    }
}
