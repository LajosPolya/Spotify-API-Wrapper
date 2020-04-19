package com.lajospolya.spotifyapiwrapper.component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

public interface ISpotifyRequestBuilder
{
    String AUTHORIZATION_HEADER = "Authorization";
    String CONTENT_TYPE_HEADER = "Content-Type";
    String IF_NOT_MATCH = "If-None-Match";
    String DELETE = "DELETE";

    void queryParam(String name, Object value);

    void queryParam(String name, List<String> values);

    <T extends Enum<T>> void queryParam(String name, List<T> values, Function<T, String> function);

    void queryParam(Map<String, Object> nameValuePair);

    void contentType(String value);

    void authorization(String token);

    void etag(String value);

    ISpotifyRequestBuilder GET();

    ISpotifyRequestBuilder POST();

    ISpotifyRequestBuilder PUT();

    ISpotifyRequestBuilder DELETE();

    ISpotifyRequestBuilder POSTWithJsonBody(Object body);

    ISpotifyRequestBuilder PUTWithJsonBody(Object body);

    ISpotifyRequestBuilder DELETEWithJsonBody(Object body);

    ISpotifyRequestBuilder POSTWithStringBody(String body);

    ISpotifyRequestBuilder PUTWithStringBody(String body);

    ISpotifyRequest<?> build();
}
