package com.lajospolya.spotifyapiwrapper.response;

import java.util.List;

/**
 * @author Lajos Polya
 * Represent the response of GetMePlayerDevices as described at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
public class Devices
{
    List<Device> devices;

    public List<Device> getDevices()
    {
        return devices;
    }

    public void setDevices(List<Device> devices)
    {
        this.devices = devices;
    }
}
