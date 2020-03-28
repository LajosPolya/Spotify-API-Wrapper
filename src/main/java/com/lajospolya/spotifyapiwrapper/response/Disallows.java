package com.lajospolya.spotifyapiwrapper.response;

/**
 * @author Lajos Polya
 * Represent a Disallows object as described at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
public class Disallows
{
    private Boolean interrupting_playback;
    private Boolean pausing;
    private Boolean resuming;
    private Boolean seeking;
    private Boolean skipping_next;
    private Boolean skipping_prev;
    private Boolean toggling_repeat_context;
    private Boolean toggling_shuffle;
    private Boolean toggling_repeat_track;
    private Boolean transferring_playback;

    public Boolean getInterrupting_playback()
    {
        return interrupting_playback;
    }

    public void setInterrupting_playback(Boolean interrupting_playback)
    {
        this.interrupting_playback = interrupting_playback;
    }

    public Boolean getPausing()
    {
        return pausing;
    }

    public void setPausing(Boolean pausing)
    {
        this.pausing = pausing;
    }

    public Boolean getResuming()
    {
        return resuming;
    }

    public void setResuming(Boolean resuming)
    {
        this.resuming = resuming;
    }

    public Boolean getSeeking()
    {
        return seeking;
    }

    public void setSeeking(Boolean seeking)
    {
        this.seeking = seeking;
    }

    public Boolean getSkipping_next()
    {
        return skipping_next;
    }

    public void setSkipping_next(Boolean skipping_next)
    {
        this.skipping_next = skipping_next;
    }

    public Boolean getSkipping_prev()
    {
        return skipping_prev;
    }

    public void setSkipping_prev(Boolean skipping_prev)
    {
        this.skipping_prev = skipping_prev;
    }

    public Boolean getToggling_repeat_context()
    {
        return toggling_repeat_context;
    }

    public void setToggling_repeat_context(Boolean toggling_repeat_context)
    {
        this.toggling_repeat_context = toggling_repeat_context;
    }

    public Boolean getToggling_shuffle()
    {
        return toggling_shuffle;
    }

    public void setToggling_shuffle(Boolean toggling_shuffle)
    {
        this.toggling_shuffle = toggling_shuffle;
    }

    public Boolean getToggling_repeat_track()
    {
        return toggling_repeat_track;
    }

    public void setToggling_repeat_track(Boolean toggling_repeat_track)
    {
        this.toggling_repeat_track = toggling_repeat_track;
    }

    public Boolean getTransferring_playback()
    {
        return transferring_playback;
    }

    public void setTransferring_playback(Boolean transferring_playback)
    {
        this.transferring_playback = transferring_playback;
    }
}
