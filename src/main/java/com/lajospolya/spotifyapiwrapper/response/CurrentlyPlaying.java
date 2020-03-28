package com.lajospolya.spotifyapiwrapper.response;

import com.lajospolya.spotifyapiwrapper.enumeration.CurrentlyPlayingType;

/**
 * @author Lajos Polya
 * Represent the response of GetMePlayerCurrentlyPlaying as described at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
public class CurrentlyPlaying
{
    private Context context;
    private Long timestamp;
    private Integer progress_ms;
    private Boolean is_playing;
    private TrackOrEpisode item;
    private CurrentlyPlayingType currently_playing_type;
    private Actions actions;

    public Context getContext()
    {
        return context;
    }

    public void setContext(Context context)
    {
        this.context = context;
    }

    public Long getTimestamp()
    {
        return timestamp;
    }

    public void setTimestamp(Long timestamp)
    {
        this.timestamp = timestamp;
    }

    public Integer getProgress_ms()
    {
        return progress_ms;
    }

    public void setProgress_ms(Integer progress_ms)
    {
        this.progress_ms = progress_ms;
    }

    public Boolean getIs_playing()
    {
        return is_playing;
    }

    public void setIs_playing(Boolean is_playing)
    {
        this.is_playing = is_playing;
    }

    public TrackOrEpisode getItem()
    {
        return item;
    }

    public void setItem(TrackOrEpisode item)
    {
        this.item = item;
    }

    public CurrentlyPlayingType getCurrently_playing_type()
    {
        return currently_playing_type;
    }

    public void setCurrently_playing_type(CurrentlyPlayingType currently_playing_type)
    {
        this.currently_playing_type = currently_playing_type;
    }

    public Actions getActions()
    {
        return actions;
    }

    public void setActions(Actions actions)
    {
        this.actions = actions;
    }
}
