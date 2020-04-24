package com.lajospolya.spotifyapiwrapper.component;

import com.google.gson.JsonSyntaxException;
import com.lajospolya.spotifyapiwrapper.component.service.HttpResponseHelper;
import com.lajospolya.spotifyapiwrapper.response.AuthenticationError;
import com.lajospolya.spotifyapiwrapper.response.SpotifyErrorContainer;
import com.lajospolya.spotifyapiwrapper.spotifyexception.SpotifyRequestAuthorizationException;
import com.lajospolya.spotifyapiwrapper.spotifyexception.SpotifyResponseException;

import java.lang.reflect.Type;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;

public class CompletableFutureAsyncResponse<T> implements ISpotifyAsyncResponse<T>
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
        serializedValue.get();
    }

    private void validateResponseAsync()
    {
        serializedValue = asyncContainer.thenApply((response) -> {
            int statusCode = response.statusCode();
            if(helper.isClientErrorStatusCode(statusCode) || helper.isServerErrorStatusCode(statusCode))
            {
                try
                {
                    SpotifyErrorContainer body = helper.serializeBody(response.body(), response.headers().map(), SpotifyErrorContainer.class);
                    if(errorConsumer != null)
                    {
                        errorConsumer.accept(body);
                    }
                    return body;
                }
                catch (JsonSyntaxException e)
                {
                    AuthenticationError authenticationError = helper.serializeBody(response.body(), response.headers().map(), AuthenticationError.class);
                    throw new SpotifyRequestAuthorizationException(authenticationError.toString());
                }

            }
            else
            {
                T body = helper.serializeBody(response.body(), response.headers().map(), type);

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
