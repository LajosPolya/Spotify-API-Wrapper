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

    ISpotifyRequestBuilder pathParam(String name, String value);

    ISpotifyRequestBuilder queryParam(String name, Object value);

    ISpotifyRequestBuilder queryParam(String name, List<String> values);

    <T extends Enum<T>> ISpotifyRequestBuilder queryParam(String name, List<T> values, Function<T, String> function);

    ISpotifyRequestBuilder queryParam(Map<String, Object> nameValuePair);

    ISpotifyRequestBuilder contentType(String value);

    ISpotifyRequestBuilder authorization(String token);

    ISpotifyRequestBuilder etag(String value);

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
