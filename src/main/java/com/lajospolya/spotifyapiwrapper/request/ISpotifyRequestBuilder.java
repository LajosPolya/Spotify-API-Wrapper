package com.lajospolya.spotifyapiwrapper.request;

import com.lajospolya.spotifyapiwrapper.internal.ISpotifyRequest;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

public interface ISpotifyRequestBuilder
{

    void queryParam(String name, Object value);

    void queryParam(String name, List<String> values);

    <T extends Enum<T>> void queryParam(String name, List<T> values, Function<T, String> function);

    void queryParam(Map<String, Object> nameValuePair);

    void contentType(String value);

    void authorization(String token);

    void etag(String value);

    SpotifyRequestBuilder GET();

    SpotifyRequestBuilder POST();

    SpotifyRequestBuilder PUT();

    SpotifyRequestBuilder DELETE();

    SpotifyRequestBuilder POSTWithJsonBody(Object body);

    SpotifyRequestBuilder PUTWithJsonBody(Object body);

    SpotifyRequestBuilder DELETEWithJsonBody(Object body);

    SpotifyRequestBuilder POSTWithStringBody(String body);

    SpotifyRequestBuilder PUTWithStringBody(String body);

    ISpotifyRequest<?> build();
}
