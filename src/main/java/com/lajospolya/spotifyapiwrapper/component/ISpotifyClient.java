package com.lajospolya.spotifyapiwrapper.component;

import java.lang.reflect.Type;

public interface ISpotifyClient
{
    <T> ISpotifyResponse<T> send(ISpotifyRequest<?> request, Type typeOfResponse);

    <T> ISpotifyAsyncResponse<T> sendAsync(ISpotifyRequest<T> request, Type typeOfResponse);
}
