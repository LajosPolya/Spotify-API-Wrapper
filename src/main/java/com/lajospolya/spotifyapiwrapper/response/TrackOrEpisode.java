package com.lajospolya.spotifyapiwrapper.response;

import java.util.List;
import java.util.Map;

/**
 * @author Lajos Polya
 * The response of GetMePlayerCurrentlyPlaying can return a Track or Episode
 * Represents a logical OR of a Track object and an Episode object.
 * Note that if a Track object is returned then the fields representing the Episode object will be null and vice
 * versa
 */
public class TrackOrEpisode
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
    private SimplifiedAlbum album;
    private Map<String, String> external_ids;
    private Integer popularity;
    private String audio_preview_url;
    private String description;
    private List<Image> images;
    private Boolean is_externally_hosted;
    private String language;
    private List<String> languages;
    private String release_date;
    private String release_date_precision;
    private ResumePoint resume_point;
    private SimplifiedShow show;

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

    public SimplifiedAlbum getAlbum()
    {
        return album;
    }

    public void setAlbum(SimplifiedAlbum album)
    {
        this.album = album;
    }

    public Map<String, String> getExternal_ids()
    {
        return external_ids;
    }

    public void setExternal_ids(Map<String, String> external_ids)
    {
        this.external_ids = external_ids;
    }

    public Integer getPopularity()
    {
        return popularity;
    }

    public void setPopularity(Integer popularity)
    {
        this.popularity = popularity;
    }

    public String getAudio_preview_url()
    {
        return audio_preview_url;
    }

    public void setAudio_preview_url(String audio_preview_url)
    {
        this.audio_preview_url = audio_preview_url;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public List<Image> getImages()
    {
        return images;
    }

    public void setImages(List<Image> images)
    {
        this.images = images;
    }

    public Boolean getIs_externally_hosted()
    {
        return is_externally_hosted;
    }

    public void setIs_externally_hosted(Boolean is_externally_hosted)
    {
        this.is_externally_hosted = is_externally_hosted;
    }

    public String getLanguage()
    {
        return language;
    }

    public void setLanguage(String language)
    {
        this.language = language;
    }

    public List<String> getLanguages()
    {
        return languages;
    }

    public void setLanguages(List<String> languages)
    {
        this.languages = languages;
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

    public ResumePoint getResume_point()
    {
        return resume_point;
    }

    public void setResume_point(ResumePoint resume_point)
    {
        this.resume_point = resume_point;
    }

    public SimplifiedShow getShow()
    {
        return show;
    }

    public void setShow(SimplifiedShow show)
    {
        this.show = show;
    }
}
