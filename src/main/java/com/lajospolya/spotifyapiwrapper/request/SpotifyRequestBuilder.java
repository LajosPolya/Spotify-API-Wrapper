package com.lajospolya.spotifyapiwrapper.request;

import com.google.gson.Gson;
import com.lajospolya.spotifyapiwrapper.internal.ISpotifyRequest;
import com.lajospolya.spotifyapiwrapper.internal.Java11HttpRequest;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.http.HttpRequest;
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
public class SpotifyRequestBuilder implements ISpotifyRequestBuilder
{
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String CONTENT_TYPE_HEADER = "Content-Type";
    private static final String IF_NOT_MATCH = "If-None-Match";
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

    @Override
    public void queryParam(String name, Object value)
    {
        uriComponentsBuilder.queryParam(name, value);
    }

    @Override
    public void queryParam(String name, List<String> values)
    {
        String commaSeparatedIds = String.join(",", values);
        uriComponentsBuilder.queryParam(name, commaSeparatedIds);
    }

    @Override
    public <T extends Enum<T>> void queryParam(String name, List<T> values, Function<T, String> function)
    {
        String commaSeparatedValues = values.stream()
                .map(function)
                .collect(Collectors.joining(","));
        uriComponentsBuilder.queryParam(name, commaSeparatedValues);
    }

    @Override
    public void queryParam(Map<String, Object> nameValuePair)
    {
        nameValuePair.forEach((name, value) -> uriComponentsBuilder.queryParam(name, value));
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
    public SpotifyRequestBuilder GET()
    {
        createBuilderWithUri().GET();
        return this;
    }

    @Override
    public SpotifyRequestBuilder POST()
    {
        createBuilderWithUri().POST(HttpRequest.BodyPublishers.noBody());
        return this;
    }

    @Override
    public SpotifyRequestBuilder PUT()
    {
        createBuilderWithUri().PUT(HttpRequest.BodyPublishers.noBody());
        return this;
    }

    @Override
    public SpotifyRequestBuilder DELETE()
    {
        createBuilderWithUri().method(DELETE, HttpRequest.BodyPublishers.noBody());
        return this;
    }

    @Override
    public SpotifyRequestBuilder POSTWithJsonBody(Object body)
    {
        createBuilderWithUri().POST(HttpRequest.BodyPublishers.ofString(gson.toJson(body)));
        return this;
    }

    @Override
    public SpotifyRequestBuilder PUTWithJsonBody(Object body)
    {
        createBuilderWithUri().PUT(HttpRequest.BodyPublishers.ofString(gson.toJson(body)));
        return this;
    }

    @Override
    public SpotifyRequestBuilder DELETEWithJsonBody(Object body)
    {
        createBuilderWithUri().method(DELETE, HttpRequest.BodyPublishers.ofString(gson.toJson(body)));
        return this;
    }

    @Override
    public SpotifyRequestBuilder POSTWithStringBody(String body)
    {
        createBuilderWithUri().POST(HttpRequest.BodyPublishers.ofString(body));
        return this;
    }

    @Override
    public SpotifyRequestBuilder PUTWithStringBody(String body)
    {
        createBuilderWithUri().PUT(HttpRequest.BodyPublishers.ofString(body));
        return this;
    }

    private HttpRequest.Builder createBuilderWithUri()
    {
        UriComponents uriComponents;
        if(pathVariable == null)
        {
            uriComponents = uriComponentsBuilder.build();
        }
        else
        {
            uriComponents = uriComponentsBuilder.buildAndExpand(pathVariable);
        }
        return requestBuilder.uri(uriComponents.toUri());
    }

    @Override
    public ISpotifyRequest<?> build()
    {
        return new Java11HttpRequest(requestBuilder.build());
    }
}
