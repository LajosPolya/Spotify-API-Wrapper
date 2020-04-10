package com.lajospolya.spotifyapiwrapper.internal;

import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;
import java.util.function.Function;

public class CompletableFutureAsyncResponse<T> implements ISpotifyAsyncResponse<CompletableFuture<?>, T>
{
    private Gson gson;
    private CompletableFuture<HttpResponse<String>> asyncContainer;
    private CompletableFuture<T> serializedValue;
    private Boolean isValid = null;

    public CompletableFutureAsyncResponse(CompletableFuture<HttpResponse<String>> asyncContainer)
    {
        this.gson = new Gson();
        this.asyncContainer = asyncContainer;
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
    public void validateResponseAsync(Function<Integer, Boolean> validateByStatusCode, Type typeOfBody)
    {
        serializedValue = asyncContainer.thenApply((response) -> {
            isValid = !validateByStatusCode.apply(response.statusCode());

            if(isValid)
            {
                String body = response.body();
                if(isStringType(typeOfBody))
                {
                    return (T) body;
                }
                return gson.fromJson(body, typeOfBody);
            }
            return null;
        });
    }

    private Boolean isStringType(Type typeOfReturnValue)
    {
        return String.class.getTypeName().equals(typeOfReturnValue.getTypeName());
    }
}
