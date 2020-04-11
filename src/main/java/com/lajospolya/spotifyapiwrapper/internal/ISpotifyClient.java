package com.lajospolya.spotifyapiwrapper.internal;

import java.lang.reflect.Type;

public interface ISpotifyClient
{
    <T> ISpotifyResponse<T> send(ISpotifyRequest<?> request, Type typeOfResponse);

    <T> ISpotifyAsyncResponse<?, T> sendAsync(ISpotifyRequest<?> request);
}
