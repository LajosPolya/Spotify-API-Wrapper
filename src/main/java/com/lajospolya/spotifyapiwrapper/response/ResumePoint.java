package com.lajospolya.spotifyapiwrapper.response;

/**
 * @author Lajos Polya
 * Represent the Resume Point object as described at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
public class ResumePoint
{
    private Boolean fully_played;
    private Integer last_position_ms;

    public Boolean getFully_played()
    {
        return fully_played;
    }

    public void setFully_played(Boolean fully_played)
    {
        this.fully_played = fully_played;
    }

    public Integer getLast_position_ms()
    {
        return last_position_ms;
    }

    public void setLast_position_ms(Integer last_position_ms)
    {
        this.last_position_ms = last_position_ms;
    }
}
