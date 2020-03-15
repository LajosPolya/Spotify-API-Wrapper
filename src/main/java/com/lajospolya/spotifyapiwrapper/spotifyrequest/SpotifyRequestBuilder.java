package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import com.google.gson.Gson;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.http.HttpRequest;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SpotifyRequestBuilder
{
    private static final String DELETE = "DELETE";
    private static final Gson gson = new Gson();
    private UriComponentsBuilder uriComponentsBuilder;
    private HttpRequest.Builder requestBuilder;
    // Only one path variable is supported
    private String pathVariable = null;

    SpotifyRequestBuilder(String uri)
    {
        this.uriComponentsBuilder = UriComponentsBuilder.fromUriString(uri);
        this.requestBuilder = HttpRequest.newBuilder();
    }

    SpotifyRequestBuilder(String uri, String pathVariable)
    {
        this(uri);
        this.pathVariable = pathVariable;
    }

    void queryParam(String name, Object value)
    {
        uriComponentsBuilder.queryParam(name, value);
    }

    void queryParam(String name, List<String> values)
    {
        String commaSeparatedIds = String.join(",", values);
        uriComponentsBuilder.queryParam(name, commaSeparatedIds);
    }

    <T extends Enum<T>> void queryParam(String name, List<T> values, Function<T, String> function)
    {
        String commaSeparatedValues = values.stream()
                .map(function)
                .collect(Collectors.joining(","));
        uriComponentsBuilder.queryParam(name, commaSeparatedValues);
    }

    void queryParam(Map<String, Object> nameValuePair)
    {
        nameValuePair.forEach((name, value) -> uriComponentsBuilder.queryParam(name, value));
    }

    void header(String name, String value)
    {
        requestBuilder.header(name, value);
    }

    HttpRequest.Builder createGetRequests()
    {
        return createBuilderWithUri()
                .GET();
    }

    HttpRequest.Builder createPostRequests()
    {
        return createBuilderWithUri()
                .POST(HttpRequest.BodyPublishers.noBody());
    }

    HttpRequest.Builder createPutRequest()
    {
        return createBuilderWithUri()
                .PUT(HttpRequest.BodyPublishers.noBody());
    }

    HttpRequest.Builder createDeleteRequest()
    {
        return createBuilderWithUri()
                .method(DELETE, HttpRequest.BodyPublishers.noBody());
    }

    HttpRequest.Builder createPostRequestWithObjectJsonBody(Object body)
    {
        return createBuilderWithUri()
                .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(body)));
    }

    HttpRequest.Builder createPutRequestWithObjectJsonBody(Object body)
    {
        return createBuilderWithUri()
                .PUT(HttpRequest.BodyPublishers.ofString(gson.toJson(body)));
    }

    HttpRequest.Builder createDeleteRequestWithObjectJsonBody(Object body)
    {
        return createBuilderWithUri()
                .method(DELETE, HttpRequest.BodyPublishers.ofString(gson.toJson(body)));
    }

    HttpRequest.Builder createPutRequestWithStringBody(String body)
    {
        return createBuilderWithUri()
                .PUT(HttpRequest.BodyPublishers.ofString(body));
    }

    private HttpRequest.Builder createBuilderWithUri()
    {
        UriComponents uriComponents;
        if(this.pathVariable == null)
        {
            uriComponents = uriComponentsBuilder.build();
        }
        else
        {
            uriComponents = uriComponentsBuilder.buildAndExpand(this.pathVariable);
        }
        return requestBuilder.uri(uriComponents.toUri());
    }
}
