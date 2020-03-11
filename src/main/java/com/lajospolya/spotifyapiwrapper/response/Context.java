package com.lajospolya.spotifyapiwrapper.response;

import java.util.Map;

public class Context
{
    private Map<String, String> external_urls;
    private String href;
    private Context type;
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

    public Context getType()
    {
        return type;
    }

    public void setType(Context type)
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
