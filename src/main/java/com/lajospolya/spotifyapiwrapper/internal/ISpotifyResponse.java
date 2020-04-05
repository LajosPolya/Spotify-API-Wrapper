package com.lajospolya.spotifyapiwrapper.internal;

import java.util.List;
import java.util.Map;

public interface ISpotifyResponse<T>
{
    String body();

    Integer statusCode();

    Map<String, List<String>> headers();
}
