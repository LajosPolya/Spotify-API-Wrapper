package com.lajospolya.spotifyapiwrapper.component;

import okhttp3.Request;

public class OkHttp4Request implements ISpotifyRequest<Request>
{
    private Request request;

    public OkHttp4Request(Request request)
    {
        this.request = request;
    }

    @Override
    public Request get()
    {
        return request;
    }
}
