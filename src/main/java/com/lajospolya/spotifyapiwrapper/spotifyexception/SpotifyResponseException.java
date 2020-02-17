package com.lajospolya.spotifyapiwrapper.spotifyexception;

import com.lajospolya.spotifyapiwrapper.response.SpotifyErrorContainer;

public class SpotifyResponseException extends RuntimeException
{
    public SpotifyResponseException(String message)
    {
        super(message);
    }

    public SpotifyResponseException(SpotifyErrorContainer error)
    {
        super(error.toString());
    }
}
