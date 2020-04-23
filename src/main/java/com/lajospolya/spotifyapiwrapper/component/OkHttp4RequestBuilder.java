package com.lajospolya.spotifyapiwrapper.component;

import com.google.gson.Gson;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class OkHttp4RequestBuilder implements ISpotifyRequestBuilder
{
    private final Gson gson = new Gson();
    private final Request.Builder requestBuilder;
    private HttpUrl.Builder urlBuilder;
    private String pathName = null;
    private String pathValue = null;

    public OkHttp4RequestBuilder(String uri)
    {
        urlBuilder = HttpUrl.parse(uri).newBuilder();
        requestBuilder = new Request.Builder();
    }

    @Override
    public void pathParam(String name, String value)
    {
        pathName = name;
        pathValue = value;
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
    public OkHttp4RequestBuilder GET()
    {
        createBuilderWithUri().get();
        return this;
    }

    @Override
    public OkHttp4RequestBuilder POST()
    {
        createBuilderWithUri().post(RequestBody.create("", null));
        return this;
    }

    @Override
    public OkHttp4RequestBuilder PUT()
    {
        createBuilderWithUri().put(RequestBody.create("", null));
        return this;
    }

    @Override
    public OkHttp4RequestBuilder DELETE()
    {
        createBuilderWithUri().delete();
        return this;
    }

    @Override
    public OkHttp4RequestBuilder POSTWithJsonBody(Object body)
    {
        String strBody = gson.toJson(body);
        createBuilderWithUri().post(RequestBody.create(strBody.getBytes()));
        return this;
    }

    @Override
    public OkHttp4RequestBuilder PUTWithJsonBody(Object body)
    {
        String strBody = gson.toJson(body);
        createBuilderWithUri().put(RequestBody.create(strBody.getBytes()));
        return this;
    }

    @Override
    public OkHttp4RequestBuilder DELETEWithJsonBody(Object body)
    {
        String strBody = gson.toJson(body);
        createBuilderWithUri().delete(RequestBody.create(strBody.getBytes()));
        return this;
    }

    @Override
    public OkHttp4RequestBuilder POSTWithStringBody(String body)
    {
        createBuilderWithUri().post(RequestBody.create(body.getBytes()));
        return this;
    }

    @Override
    public OkHttp4RequestBuilder PUTWithStringBody(String body)
    {
        createBuilderWithUri().put(RequestBody.create(body.getBytes()));
        return this;
    }

    private Request.Builder createBuilderWithUri()
    {
        if(pathName != null && pathValue != null)
        {

            String uriWithPathVariable = urlBuilder.toString().replace(
                    URLEncoder.encode(pathName, StandardCharsets.UTF_8.toString()), pathValue);
            urlBuilder = HttpUrl.parse(uriWithPathVariable).newBuilder();
        }
        HttpUrl url = urlBuilder.build();
        return requestBuilder.url(url);
    }

    @Override
    public ISpotifyRequest<?> build()
    {
        return new OkHttp4Request(requestBuilder.build());
    }
}
