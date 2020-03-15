package com.lajospolya.spotifyapiwrapper.body;

public class AuthorizationCode implements UrlEncoded
{
    private String code;
    private String redirectUri;

    public AuthorizationCode(String code, String redirectUri)
    {
        this.code = code;
        this.redirectUri = redirectUri;
    }

    @Override
    public String toUrlEncodedString()
    {
        return "grant_type=authorization_code&code=" + code + "&redirect_uri=" + redirectUri;
    }
}
