package com.lajospolya.spotifyapiwrapper.response;

import com.lajospolya.spotifyapiwrapper.enumeration.DeviceType;

/**
 * @author Lajos Polya
 * Represent a Device object as described at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
public class Device
{
    private String id;
    private Boolean is_active;
    private Boolean is_private_session;
    private Boolean is_restricted;
    private String name;
    private DeviceType type;
    private Integer volume_percent;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public Boolean getIs_active()
    {
        return is_active;
    }

    public void setIs_active(Boolean is_active)
    {
        this.is_active = is_active;
    }

    public Boolean getIs_private_session()
    {
        return is_private_session;
    }

    public void setIs_private_session(Boolean is_private_session)
    {
        this.is_private_session = is_private_session;
    }

    public Boolean getIs_restricted()
    {
        return is_restricted;
    }

    public void setIs_restricted(Boolean is_restricted)
    {
        this.is_restricted = is_restricted;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public DeviceType getType()
    {
        return type;
    }

    public void setType(DeviceType type)
    {
        this.type = type;
    }

    public Integer getVolume_percent()
    {
        return volume_percent;
    }

    public void setVolume_percent(Integer volume_percent)
    {
        this.volume_percent = volume_percent;
    }
}
