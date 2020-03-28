package com.lajospolya.spotifyapiwrapper.response;

import java.util.List;

/**
 * @author Lajos Polya
 * Represent the response of GetArtistsAlbums as described at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
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
