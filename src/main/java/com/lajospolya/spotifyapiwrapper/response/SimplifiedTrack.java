package com.lajospolya.spotifyapiwrapper.response;

import java.util.List;
import java.util.Map;

/**
 * @author Lajos Polya
 * Represent the Track (Simplified) object as described at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
public class SimplifiedTrack
{
    private List<SimplifiedArtist> artists;
    private List<String> available_markets;
    private Integer disc_number;
    private Integer duration_ms;
    private Boolean explicit;
    private Map<String, String> external_urls;
    private String href;
    private String id;
    private Boolean is_playable;
    private TrackLink linked_form;
    private Map<String, String> restrictions;
    private String name;
    private String preview_url;
    private Integer track_number;
    private String type;
    private String uri;
    private Boolean is_local;

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

    public Integer getDisc_number()
    {
        return disc_number;
    }

    public void setDisc_number(Integer disc_number)
    {
        this.disc_number = disc_number;
    }

    public Integer getDuration_ms()
    {
        return duration_ms;
    }

    public void setDuration_ms(Integer duration_ms)
    {
        this.duration_ms = duration_ms;
    }

    public Boolean getExplicit()
    {
        return explicit;
    }

    public void setExplicit(Boolean explicit)
    {
        this.explicit = explicit;
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

    public Boolean getIs_playable()
    {
        return is_playable;
    }

    public void setIs_playable(Boolean is_playable)
    {
        this.is_playable = is_playable;
    }

    public TrackLink getLinked_form()
    {
        return linked_form;
    }

    public void setLinked_form(TrackLink linked_form)
    {
        this.linked_form = linked_form;
    }

    public Map<String, String> getRestrictions()
    {
        return restrictions;
    }

    public void setRestrictions(Map<String, String> restrictions)
    {
        this.restrictions = restrictions;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getPreview_url()
    {
        return preview_url;
    }

    public void setPreview_url(String preview_url)
    {
        this.preview_url = preview_url;
    }

    public Integer getTrack_number()
    {
        return track_number;
    }

    public void setTrack_number(Integer track_number)
    {
        this.track_number = track_number;
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

    public Boolean getIs_local()
    {
        return is_local;
    }

    public void setIs_local(Boolean is_local)
    {
        this.is_local = is_local;
    }
}
