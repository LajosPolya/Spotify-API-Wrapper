package com.lajospolya.spotifyapiwrapper.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

/**
 * @author Lajos Polya
 * Represent the Playlist (Simplified) object as described at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
public class SimplifiedPlaylist
{
    private Boolean collaborative;
    private String description;
    private Map<String, String> external_urls;
    private String href;
    private String id;
    private List<Image> images;
    private String name;
    private UserPublic owner;
    @SerializedName("public") private Boolean isPublic;
    private String snapshot_id;
    private PlaylistsTracks tracks;
    private String type;
    private String uri;

    public Boolean getCollaborative()
    {
        return collaborative;
    }

    public void setCollaborative(Boolean collaborative)
    {
        this.collaborative = collaborative;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
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

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
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

    public UserPublic getOwner()
    {
        return owner;
    }

    public void setOwner(UserPublic owner)
    {
        this.owner = owner;
    }

    public Boolean getPublic()
    {
        return isPublic;
    }

    public void setPublic(Boolean aPublic)
    {
        isPublic = aPublic;
    }

    public String getSnapshot_id()
    {
        return snapshot_id;
    }

    public void setSnapshot_id(String snapshot_id)
    {
        this.snapshot_id = snapshot_id;
    }

    public PlaylistsTracks getTracks()
    {
        return tracks;
    }

    public void setTracks(PlaylistsTracks tracks)
    {
        this.tracks = tracks;
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
