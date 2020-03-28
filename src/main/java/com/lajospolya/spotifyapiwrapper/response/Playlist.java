package com.lajospolya.spotifyapiwrapper.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

/**
 * @author Lajos Polya
 * Represent the response of GetPlaylist as described at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
public class Playlist extends CacheableResponse
{
    private Boolean collaborative;
    private String description;
    private Map<String, String> external_urls;
    private Follower followers;
    private String href;
    private String id;
    private List<Image> images;
    private String name;
    private UserPublic owner;
    @SerializedName("public") private Boolean isPublic;
    private String snapshot_id;
    private Paging<PlaylistTrack> tracks;
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

    public Follower getFollowers()
    {
        return followers;
    }

    public void setFollowers(Follower followers)
    {
        this.followers = followers;
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

    public Paging<PlaylistTrack> getTracks()
    {
        return tracks;
    }

    public void setTracks(Paging<PlaylistTrack> tracks)
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
