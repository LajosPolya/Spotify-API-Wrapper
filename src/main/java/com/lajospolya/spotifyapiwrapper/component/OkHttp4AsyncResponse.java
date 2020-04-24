package com.lajospolya.spotifyapiwrapper.component;

import com.google.gson.JsonSyntaxException;
import com.lajospolya.spotifyapiwrapper.component.service.HttpResponseHelper;
import com.lajospolya.spotifyapiwrapper.response.AuthenticationError;
import com.lajospolya.spotifyapiwrapper.response.SpotifyErrorContainer;
import com.lajospolya.spotifyapiwrapper.spotifyexception.SpotifyRequestAuthorizationException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class OkHttp4AsyncResponse<T> implements ISpotifyAsyncResponse<T>
{
    private final HttpResponseHelper helper;
    private final Call call;
    private final Type type;
    // Can be either of type T (on success) or type SpotifyErrorContainer (on error)
    private CompletableFuture<?> serializedValue;

    private Consumer<T> successConsumer = null;
    private Consumer<SpotifyErrorContainer> errorConsumer = null;

    public OkHttp4AsyncResponse(Call call, Type type)
    {
        this.call = call;
        this.helper = new HttpResponseHelper();
        this.type = type;
        validateResponseAsync();
    }

    private void validateResponseAsync()
    {

        Callback callback = new Callback()
        {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e)
            {
                System.out.println(e);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException
            {
                System.out.println(response);
                int statusCode = response.code();
                if(helper.isClientErrorStatusCode(statusCode) || helper.isServerErrorStatusCode(statusCode))
                {
                    try
                    {
                        SpotifyErrorContainer body = helper.serializeBody(response.body().string(), response.headers().toMultimap(), SpotifyErrorContainer.class);
                        if(errorConsumer != null)
                        {
                            errorConsumer.accept(body);
                        }
                    }
                    catch (JsonSyntaxException e)
                    {
                        AuthenticationError authenticationError = helper.serializeBody(response.body().string(), response.headers().toMultimap(), AuthenticationError.class);
                        throw new SpotifyRequestAuthorizationException(authenticationError.toString());
                    }

                }
                else
                {
                    T body = helper.serializeBody(response.body().string(), response.headers().toMultimap(), type);

                    if(successConsumer != null)
                    {
                        successConsumer.accept(body);
                    }
                }
            }
        };
        call.enqueue(callback);
    }

    /**
     * This implementation (OkHttp4) does not support the blocking operation
     */
    @Override
    public void block()
    {
        throw new UnsupportedOperationException("Cannot block async request with OkHttp");
    }

    @Override
    public ISpotifyAsyncResponse<T> success(Consumer<T> func)
    {
        successConsumer = func;
        return this;
    }

    @Override
    public ISpotifyAsyncResponse<T> error(Consumer<SpotifyErrorContainer> func)
    {
        errorConsumer = func;
        return this;
    }

    @Override
    public boolean isDone()
    {
        return call.isExecuted();
    }
}
