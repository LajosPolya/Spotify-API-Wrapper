package com.lajospolya.spotifyapiwrapper.spotifyexception;

public class SpotifyRequestAuthorizationException extends RuntimeException
{
    public SpotifyRequestAuthorizationException(String message)
    {
        super(message);
    }
}
