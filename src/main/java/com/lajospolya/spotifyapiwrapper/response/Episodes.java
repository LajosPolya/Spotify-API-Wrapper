package com.lajospolya.spotifyapiwrapper.response;

import java.util.List;

public class Episodes
{
    List<Episode> episodes;

    public List<Episode> getEpisodes()
    {
        return episodes;
    }

    public void setEpisodes(List<Episode> episodes)
    {
        this.episodes = episodes;
    }
}
