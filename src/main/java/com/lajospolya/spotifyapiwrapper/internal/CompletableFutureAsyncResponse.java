package com.lajospolya.spotifyapiwrapper.internal;

import com.lajospolya.spotifyapiwrapper.response.SpotifyErrorContainer;

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
    private CompletableFuture<T> serializedTypedValue;
    private CompletableFuture<?> serializedValue;
    private Boolean erroneous = false;

    public CompletableFutureAsyncResponse(CompletableFuture<HttpResponse<String>> asyncContainer, Type type)
    {
        this.helper = new HttpResponseHelper();
        this.asyncContainer = asyncContainer;
        this.type = type;
        validateResponseAsync();
    }

    // TODO: this should not be a method
    @Override
    public CompletableFutureAsyncResponse<T> thenAccept(Consumer<? super T> action)
    {
        // Fix this:
        if(!erroneous)
        {
            serializedTypedValue = (CompletableFuture<T>) serializedValue;
            asyncContainer.thenAccept((response) -> serializedTypedValue.thenAccept(action));
        }
        return this;
    }

    @Override
    public T get() throws ExecutionException, InterruptedException
    {

        asyncContainer.get();
        return (T)serializedValue.get();
    }

    private void validateResponseAsync()
    {
        serializedValue = asyncContainer.thenApply((response) -> {
            int statusCode = response.statusCode();
            if(helper.isClientErrorStatusCode(statusCode) || helper.isServerErrorStatusCode(statusCode))
            {
                erroneous = true;
                return helper.serializeBody(response, SpotifyErrorContainer.class);
            }
            else
            {
                T body = helper.serializeBody(response, type);
                /*
                 * when a 304 is returned with an empty body the serialized body becomes null
                 * so we don't need to handled caching separately
                 */
                helper.setCachableValuesFromHeadersIfCachable(body,response);
                return body;
            }
        });
    }

    private Boolean isStringType(Type typeOfReturnValue)
    {
        return String.class.getTypeName().equals(typeOfReturnValue.getTypeName());
    }
}
