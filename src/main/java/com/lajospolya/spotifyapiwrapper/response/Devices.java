package com.lajospolya.spotifyapiwrapper.response;

import java.util.List;

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
