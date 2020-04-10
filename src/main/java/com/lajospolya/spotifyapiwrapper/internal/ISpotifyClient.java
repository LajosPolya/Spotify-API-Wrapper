package com.lajospolya.spotifyapiwrapper.internal;

public interface ISpotifyClient
{
    <T> ISpotifyResponse<T> send(ISpotifyRequest<?> request);

    <T> ISpotifyAsyncResponse<?, T> sendAsync(ISpotifyRequest<?> request);
}
