package com.lajospolya.spotifyapiwrapper.response;

public class AuthorizationCodeFlowResponse extends ClientCredentialsFlowResponse
{
    private String refresh_token;

    public String getRefresh_token()
    {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token)
    {
        this.refresh_token = refresh_token;
    }
}
