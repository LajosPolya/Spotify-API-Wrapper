package com.lajospolya.spotifyapiwrapper.response;

public class AuthorizationResponse
{
    private String access_token;
    private String token_type;
    private Integer expires_in;
    private String scope;

    public String getAccessToken()
    {
        return access_token;
    }

    public void setAccessToken(String access_token)
    {
        this.access_token = access_token;
    }

    public String getTokenType()
    {
        return token_type;
    }

    public void setTokenType(String token_type)
    {
        this.token_type = token_type;
    }

    public Integer getExpiresIn()
    {
        return expires_in;
    }

    public void setExpiresIn(Integer expires_in)
    {
        this.expires_in = expires_in;
    }

    public String getScope()
    {
        return scope;
    }

    public void setScope(String scope)
    {
        this.scope = scope;
    }
}
