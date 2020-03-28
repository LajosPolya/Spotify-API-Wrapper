package com.lajospolya.spotifyapiwrapper.response;

import java.util.List;
import java.util.Map;

public class AlbumBase
{
    private String album_type;
    private List<SimplifiedArtist> artists;
    private List<String> available_markets;
    private Map<String, String> external_urls;
    private String href;
    private List<Image> images;
    private String name;
    private String release_date;
    private String release_date_precision;
    private Map<String, String> restrictions;
    private String type;
    private String uri;

    public String getAlbum_type()
    {
        return album_type;
    }

    public void setAlbum_type(String album_type)
    {
        this.album_type = album_type;
    }

    public List<SimplifiedArtist> getArtists()
    {
        return artists;
    }

    public void setArtists(List<SimplifiedArtist> artists)
    {
        this.artists = artists;
    }

    public List<String> getAvailable_markets()
    {
        return available_markets;
    }

    public void setAvailable_markets(List<String> available_markets)
    {
        this.available_markets = available_markets;
    }

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

    public List<Image> getImages()
    {
        return images;
    }

    public void setImages(List<Image> images)
    {
        this.images = images;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getRelease_date()
    {
        return release_date;
    }

    public void setRelease_date(String release_date)
    {
        this.release_date = release_date;
    }

    public String getRelease_date_precision()
    {
        return release_date_precision;
    }

    public void setRelease_date_precision(String release_date_precision)
    {
        this.release_date_precision = release_date_precision;
    }

    public Map<String, String> getRestrictions()
    {
        return restrictions;
    }

    public void setRestrictions(Map<String, String> restrictions)
    {
        this.restrictions = restrictions;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
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
