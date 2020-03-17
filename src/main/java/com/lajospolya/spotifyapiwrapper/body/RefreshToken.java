package com.lajospolya.spotifyapiwrapper.body;

public class RefreshToken implements UrlEncoded
{
    private String refresh_token;

    public RefreshToken(String refresh_token)
    {
        this.refresh_token = refresh_token;
    }

    @Override
    public String toUrlEncodedString()
    {
        return "grant_type=refresh_token&refresh_token=" + refresh_token;
    }
}
