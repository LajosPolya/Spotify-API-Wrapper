package com.lajospolya.spotifyapiwrapper.component;

import java.net.http.HttpRequest;

public class Java11HttpRequest implements ISpotifyRequest<HttpRequest>
{
    private final HttpRequest request;

    public Java11HttpRequest(HttpRequest request)
    {
        this.request = request;
    }

    @Override
    public HttpRequest get()
    {
        return request;
    }
}
