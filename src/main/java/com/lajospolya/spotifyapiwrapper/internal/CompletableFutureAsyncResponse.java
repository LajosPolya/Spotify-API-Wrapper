package com.lajospolya.spotifyapiwrapper.internal;

import com.lajospolya.spotifyapiwrapper.response.SpotifyErrorContainer;
import com.lajospolya.spotifyapiwrapper.spotifyexception.SpotifyResponseException;

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
    // Can be either of type T (on success) or type SpotifyErrorContainer (on error)
    private CompletableFuture<?> serializedValue;

    private Consumer<T> successConsumer = null;
    private Consumer<SpotifyErrorContainer> errorConsumer = null;

    public CompletableFutureAsyncResponse(CompletableFuture<HttpResponse<String>> asyncContainer, Type type)
    {
        this.helper = new HttpResponseHelper();
        this.asyncContainer = asyncContainer;
        this.type = type;
        validateResponseAsync();
    }

    @Override
    public void block() throws ExecutionException, InterruptedException
    {
        asyncContainer.get();
        serializedValue.get();
    }

    private void validateResponseAsync()
    {
        serializedValue = asyncContainer.thenApply((response) -> {
            int statusCode = response.statusCode();
            if(helper.isClientErrorStatusCode(statusCode) || helper.isServerErrorStatusCode(statusCode))
            {
                SpotifyErrorContainer body = helper.serializeBody(response, SpotifyErrorContainer.class);
                if(errorConsumer != null)
                {
                    errorConsumer.accept(body);
                }
                return body;
            }
            else
            {
                T body = helper.serializeBody(response, type);

                if(successConsumer != null)
                {
                    successConsumer.accept(body);
                }
                return body;
            }
        });
    }

    @Override
    public CompletableFutureAsyncResponse<T> success(Consumer<T> func) throws SpotifyResponseException
    {
        if(successConsumer != null)
        {
            throw new SpotifyResponseException("Can only have one Success consumer");
        }
        successConsumer = func;
        return this;
    }

    @Override
    public CompletableFutureAsyncResponse<T> error(Consumer<SpotifyErrorContainer> func) throws SpotifyResponseException
    {
        if(errorConsumer != null)
        {
            throw new SpotifyResponseException("Can only have one Error consumer");
        }
        errorConsumer = func;
        return this;
    }

    @Override
    public boolean isDone()
    {
        return serializedValue.isDone();
    }
}
