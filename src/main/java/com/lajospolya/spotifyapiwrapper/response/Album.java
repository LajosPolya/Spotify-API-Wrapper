package com.lajospolya.spotifyapiwrapper.response;

import java.util.List;
import java.util.Map;

public class Album extends AlbumBase
{
    private List<Map<String, String>> copyrights;
    private Map<String, String> external_ids;
    private List<String> genres;
    private String id;
    private String label;
    private Integer popularity;
    private Paging<SimplifiedTrack> tracks;

    public List<Map<String, String>> getCopyrights()
    {
        return copyrights;
    }

    public void setCopyrights(List<Map<String, String>> copyrights)
    {
        this.copyrights = copyrights;
    }

    public Map<String, String> getExternal_ids()
    {
        return external_ids;
    }

    public void setExternal_ids(Map<String, String> external_ids)
    {
        this.external_ids = external_ids;
    }

    public List<String> getGenres()
    {
        return genres;
    }

    public void setGenres(List<String> genres)
    {
        this.genres = genres;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getLabel()
    {
        return label;
    }

    public void setLabel(String label)
    {
        this.label = label;
    }

    public Integer getPopularity()
    {
        return popularity;
    }

    public void setPopularity(Integer popularity)
    {
        this.popularity = popularity;
    }

    public Paging<SimplifiedTrack> getTracks()
    {
        return tracks;
    }

    public void setTracks(Paging<SimplifiedTrack> tracks)
    {
        this.tracks = tracks;
    }
}
