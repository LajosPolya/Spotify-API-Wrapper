package com.lajospolya.spotifyapiwrapper.cachable;

public class CachableResponse
{
    private String etag;

    public String getEtag()
    {
        return etag;
    }

    public void setEtag(String etag)
    {
        this.etag = etag;
    }
}
