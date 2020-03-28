package com.lajospolya.spotifyapiwrapper.response;

import com.lajospolya.spotifyapiwrapper.enumeration.ContextType;

import java.util.Map;

/**
 * @author Lajos Polya
 * Represent a Context object as described at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
public class Context
{
    private Map<String, String> external_urls;
    private String href;
    private ContextType type;
    private String uri;

    public Map<String, String> getExternal_urls()
    {
        return external_urls;
    }

    public void setExternal_urls(Map<String, String> external_urls)
    {
        this.external_urls = external_urls;
    }

    public String getHref()
    {
        return href;
    }

    public void setHref(String href)
    {
        this.href = href;
    }

    public ContextType getContextType()
    {
        return type;
    }

    public void setContextType(ContextType type)
    {
        this.type = type;
    }

    public String getUri()
    {
        return uri;
    }

    public void setUri(String uri)
    {
        this.uri = uri;
    }
}
