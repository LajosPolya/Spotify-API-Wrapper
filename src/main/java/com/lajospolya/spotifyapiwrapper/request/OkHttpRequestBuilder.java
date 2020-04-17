package com.lajospolya.spotifyapiwrapper.request;

import com.lajospolya.spotifyapiwrapper.internal.ISpotifyRequest;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class OkHttpRequestBuilder implements ISpotifyRequestBuilder
{
    private HttpUrl url;
    private HttpUrl.Builder urlBuilder;
    private Request.Builder requestBuilder;
    private String pathVariable = null;

    OkHttpRequestBuilder(String uri)
    {
        urlBuilder = HttpUrl.parse(uri).newBuilder();
        requestBuilder = new Request.Builder();
    }

    OkHttpRequestBuilder(String uri, String pathVariable)
    {
        uri = uri.replace("{id}", pathVariable);
        urlBuilder = HttpUrl.parse(uri).newBuilder();
        requestBuilder = new Request.Builder();
        this.pathVariable = pathVariable;
    }

    @Override
    public void queryParam(String name, Object value)
    {
        urlBuilder.addQueryParameter(name, value.toString());
    }

    @Override
    public void queryParam(String name, List<String> values)
    {
        String commaSeparatedIds = String.join(",", values);
        urlBuilder.addQueryParameter(name, commaSeparatedIds);
    }

    @Override
    public <T extends Enum<T>> void queryParam(String name, List<T> values, Function<T, String> function)
    {
        String commaSeparatedValues = values.stream()
                .map(function)
                .collect(Collectors.joining(","));
        urlBuilder.addQueryParameter(name, commaSeparatedValues);
    }

    @Override
    public void queryParam(Map<String, Object> nameValuePair)
    {
        nameValuePair.forEach((name, value) -> urlBuilder.addQueryParameter(name, value.toString()));
    }

    @Override
    public void contentType(String value)
    {
        requestBuilder.header(CONTENT_TYPE_HEADER, value);
    }

    @Override
    public void authorization(String token)
    {
        requestBuilder.header(AUTHORIZATION_HEADER, token);
    }

    @Override
    public void etag(String value)
    {
        requestBuilder.header(IF_NOT_MATCH, value);
    }

    @Override
    public OkHttpRequestBuilder GET()
    {
        createBuilderWithUri().get();
        return this;
    }

    @Override
    public OkHttpRequestBuilder POST()
    {
        createBuilderWithUri().post(RequestBody.create("", null));
        return this;
    }

    @Override
    public OkHttpRequestBuilder PUT()
    {
        createBuilderWithUri().put(RequestBody.create("", null));
        return this;
    }

    @Override
    public OkHttpRequestBuilder DELETE()
    {
        createBuilderWithUri().delete();
        return this;
    }

    @Override
    public OkHttpRequestBuilder POSTWithJsonBody(Object body)
    {
        return null;
    }

    @Override
    public OkHttpRequestBuilder PUTWithJsonBody(Object body)
    {
        return null;
    }

    @Override
    public OkHttpRequestBuilder DELETEWithJsonBody(Object body)
    {
        return null;
    }

    @Override
    public OkHttpRequestBuilder POSTWithStringBody(String body)
    {
        return null;
    }

    @Override
    public OkHttpRequestBuilder PUTWithStringBody(String body)
    {
        return null;
    }

    private Request.Builder createBuilderWithUri()
    {
        url = urlBuilder.build();
        return requestBuilder.url(url);
    }

    @Override
    public ISpotifyRequest<?> build()
    {
        return null;
    }
}
