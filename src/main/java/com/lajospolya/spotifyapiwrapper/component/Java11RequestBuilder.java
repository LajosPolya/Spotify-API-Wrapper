package com.lajospolya.spotifyapiwrapper.component;

import com.google.gson.Gson;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.http.HttpRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Lajos Polya
 *
 * This class allows for easy building of Spotify requests.
 * It contains all methods to add appropriate headers, and query params, as well as a single path param.
 * It also has method to easily build DELETE, PUT, POST, and GET requests
 */
public class Java11RequestBuilder implements ISpotifyRequestBuilder
{
    private static final Gson gson = new Gson();
    private final UriComponentsBuilder uriComponentsBuilder;
    private final HttpRequest.Builder requestBuilder;
    // Only one path variable is supported
    private Map<String, String> pathVars;

    public Java11RequestBuilder(String uri)
    {
        this.uriComponentsBuilder = UriComponentsBuilder.fromUriString(uri);
        this.requestBuilder = HttpRequest.newBuilder();
    }

    @Override
    public Java11RequestBuilder pathParam(String name, String value)
    {
        pathVars = new HashMap<>();
        name = name.substring(1, name.length() - 1);
        pathVars.put(name, value);
        return this;
    }

    @Override
    public Java11RequestBuilder queryParam(String name, Object value)
    {
        uriComponentsBuilder.queryParam(name, value);
        return this;
    }

    @Override
    public Java11RequestBuilder queryParam(String name, List<String> values)
    {
        String commaSeparatedIds = String.join(",", values);
        uriComponentsBuilder.queryParam(name, commaSeparatedIds);
        return this;
    }

    @Override
    public <T extends Enum<T>> Java11RequestBuilder queryParam(String name, List<T> values, Function<T, String> function)
    {
        String commaSeparatedValues = values.stream()
                .map(function)
                .collect(Collectors.joining(","));
        uriComponentsBuilder.queryParam(name, commaSeparatedValues);
        return this;
    }

    @Override
    public Java11RequestBuilder queryParam(Map<String, Object> nameValuePair)
    {
        nameValuePair.forEach((name, value) -> uriComponentsBuilder.queryParam(name, value));
        return this;
    }

    @Override
    public Java11RequestBuilder contentType(String value)
    {
        requestBuilder.header(CONTENT_TYPE_HEADER, value);
        return this;
    }

    @Override
    public Java11RequestBuilder authorization(String token)
    {
        requestBuilder.header(AUTHORIZATION_HEADER, token);
        return this;
    }

    @Override
    public Java11RequestBuilder etag(String value)
    {
        requestBuilder.header(IF_NOT_MATCH, value);
        return this;
    }

    @Override
    public Java11RequestBuilder GET()
    {
        createBuilderWithUri().GET();
        return this;
    }

    @Override
    public Java11RequestBuilder POST()
    {
        createBuilderWithUri().POST(HttpRequest.BodyPublishers.noBody());
        return this;
    }

    @Override
    public Java11RequestBuilder PUT()
    {
        createBuilderWithUri().PUT(HttpRequest.BodyPublishers.noBody());
        return this;
    }

    @Override
    public Java11RequestBuilder DELETE()
    {
        createBuilderWithUri().method(DELETE, HttpRequest.BodyPublishers.noBody());
        return this;
    }

    @Override
    public Java11RequestBuilder POSTWithJsonBody(Object body)
    {
        createBuilderWithUri().POST(HttpRequest.BodyPublishers.ofString(gson.toJson(body)));
        return this;
    }

    @Override
    public Java11RequestBuilder PUTWithJsonBody(Object body)
    {
        createBuilderWithUri().PUT(HttpRequest.BodyPublishers.ofString(gson.toJson(body)));
        return this;
    }

    @Override
    public Java11RequestBuilder DELETEWithJsonBody(Object body)
    {
        createBuilderWithUri().method(DELETE, HttpRequest.BodyPublishers.ofString(gson.toJson(body)));
        return this;
    }

    @Override
    public Java11RequestBuilder POSTWithStringBody(String body)
    {
        createBuilderWithUri().POST(HttpRequest.BodyPublishers.ofString(body));
        return this;
    }

    @Override
    public Java11RequestBuilder PUTWithStringBody(String body)
    {
        createBuilderWithUri().PUT(HttpRequest.BodyPublishers.ofString(body));
        return this;
    }

    private HttpRequest.Builder createBuilderWithUri()
    {
        UriComponents uriComponents;
        if(pathVars == null)
        {
            uriComponents = uriComponentsBuilder.build();
        }
        else
        {
            uriComponents = uriComponentsBuilder.buildAndExpand(pathVars);
        }
        return requestBuilder.uri(uriComponents.toUri());
    }

    @Override
    public ISpotifyRequest<?> build()
    {
        return new Java11HttpRequest(requestBuilder.build());
    }
}
