package com.lajospolya.spotifyapiwrapper.internal;

import com.lajospolya.spotifyapiwrapper.response.SpotifyErrorContainer;

import java.util.List;
import java.util.Map;

public interface ISpotifyResponse<ResponseType>
{
    ResponseType body();

    SpotifyErrorContainer error();

    Integer statusCode();

    Map<String, List<String>> headers();
}
