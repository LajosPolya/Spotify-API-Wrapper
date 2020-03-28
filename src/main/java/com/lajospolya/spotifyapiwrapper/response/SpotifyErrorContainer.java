package com.lajospolya.spotifyapiwrapper.response;

/**
 * @author Lajos Polya
 * Represent the container for an errpr response
 */
public class SpotifyErrorContainer
{
    private SpotifyError error;

    @Override
    public String toString()
    {
        return "Returned a status of \"" + error.getStatus() + "\" with error message \"" + error.getMessage() + "\"";
    }

    public SpotifyError getError()
    {
        return error;
    }

    public void setError(SpotifyError error)
    {
        this.error = error;
    }
}
