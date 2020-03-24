package com.lajospolya.spotifyapiwrapper.response;

import com.lajospolya.spotifyapiwrapper.enumeration.Explicit;

import java.util.List;
import java.util.Map;

public class SimplifiedEpisode
{
    private String audio_preview_url;
    private String description;
    private Integer duration_ms;
    private Explicit explicit;
    private Map<String, String> external_urls;
    private String href;
    private String id;
    private List<Image> images;
    private Boolean is_externally_hosted;
    private Boolean is_playable;
    private String language;
    private List<String> languages;
    private String name;
    private String release_date;
    private String release_date_precision;
    private ResumePoint resume_point;
    private String type;
    private String uri;

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

    public Integer getDuration_ms()
    {
        return duration_ms;
    }

    public void setDuration_ms(Integer duration_ms)
    {
        this.duration_ms = duration_ms;
    }

    public Explicit getExplicit()
    {
        return explicit;
    }

    public void setExplicit(Explicit explicit)
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

    public Boolean getIs_playable()
    {
        return is_playable;
    }

    public void setIs_playable(Boolean is_playable)
    {
        this.is_playable = is_playable;
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

    public ResumePoint getResume_point()
    {
        return resume_point;
    }

    public void setResume_point(ResumePoint resume_point)
    {
        this.resume_point = resume_point;
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
