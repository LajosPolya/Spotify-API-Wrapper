package com.lajospolya.spotifyapiwrapper.internal;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class Java11HttpResponse implements ISpotifyResponse<HttpResponse<String>>
{
    private HttpResponse<String> response;

    public Java11HttpResponse(HttpResponse<String> response)
    {
        this.response = response;
    }

    @Override
    public String body()
    {
        return response.body();
    }

    @Override
    public Integer statusCode()
    {
        return response.statusCode();
    }

    @Override
    public Map<String, List<String>> headers()
    {
        return response.headers().map();
    }


}
