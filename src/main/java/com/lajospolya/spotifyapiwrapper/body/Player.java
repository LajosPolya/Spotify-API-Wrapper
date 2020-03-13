package com.lajospolya.spotifyapiwrapper.body;

import java.util.List;

public class Player
{
    private List<String> device_ids;
    private Boolean play;

    public Player(List<String> device_ids, Boolean play)
    {
        this.device_ids = device_ids;
        this.play = play;
    }
}
