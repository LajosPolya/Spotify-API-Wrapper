package com.lajospolya.spotifyapiwrapper.internal;

public interface ISpotifyClient
{
    <T> ISpotifyResponse<T> send(ISpotifyRequest<?> request);

    ISpotifyAsyncResponse<?, ?> sendAsync(ISpotifyRequest<?> request);
}
