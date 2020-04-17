package com.lajospolya.spotifyapiwrapper.internal;

import okhttp3.Request;

public class OkHttpRequest implements ISpotifyRequest<Request>
{
    private Request request;

    public OkHttpRequest(Request request)
    {
        this.request = request;
    }

    @Override
    public Request get()
    {
        return request;
    }
}
