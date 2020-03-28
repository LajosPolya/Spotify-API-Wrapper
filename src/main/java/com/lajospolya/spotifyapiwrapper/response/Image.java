package com.lajospolya.spotifyapiwrapper.response;

/**
 * @author Lajos Polya
 * Represent the Image object as described at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
public class Image
{
    private Integer height;
    private String url;
    private Integer width;

    public Integer getHeight()
    {
        return height;
    }

    public void setHeight(Integer height)
    {
        this.height = height;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public Integer getWidth()
    {
        return width;
    }

    public void setWidth(Integer width)
    {
        this.width = width;
    }
}
