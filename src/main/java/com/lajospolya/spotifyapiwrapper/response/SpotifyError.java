package com.lajospolya.spotifyapiwrapper.response;

public class SpotifyError
{
    Integer status;
    String message;

    public Integer getStatus()
    {
        return status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }
}
