package com.lajospolya.spotifyapiwrapper.cachable;

/**
 * @author Lajos Polya
 *
 * This class is the base class for all responses which may be cached.
 * If the reponse header has an etag then it will be autopopulated in this class.
 */
public class CacheableResponse
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
