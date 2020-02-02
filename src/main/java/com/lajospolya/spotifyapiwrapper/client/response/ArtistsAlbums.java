package com.lajospolya.spotifyapiwrapper.client.response;

import java.util.List;

public class ArtistsAlbums
{
    private String href;
    private List<SimplifiedAlbum> items;

    public String getHref()
    {
        return href;
    }

    public void setHref(String href)
    {
        this.href = href;
    }

    public List<SimplifiedAlbum> getItems()
    {
        return items;
    }

    public void setItems(List<SimplifiedAlbum> items)
    {
        this.items = items;
    }
}
