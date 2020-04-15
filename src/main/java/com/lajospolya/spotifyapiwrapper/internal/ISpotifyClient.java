package com.lajospolya.spotifyapiwrapper.internal;

public interface ISpotifyClient
{
    ISpotifyResponse<?> send(ISpotifyRequest<?> request);

    ISpotifyAsyncResponse<?, ?> sendAsync(ISpotifyRequest<?> request);
}
