package com.lajospolya.spotifyapiwrapper.spotifyexception;

public class SpotifyResponseException extends RuntimeException
{
    private SpotifyErrorContainer error;

    public SpotifyResponseException(SpotifyErrorContainer error)
    {
        super(error.toString());
        this.error = error;
    }

    public SpotifyErrorContainer getError()
    {
        return error;
    }

    public void setError(SpotifyErrorContainer error)
    {
        this.error = error;
    }
}
