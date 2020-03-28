package com.lajospolya.spotifyapiwrapper.response;

/**
 * @author Lajos Polya
 * Represent the response of GetEpisodes as described at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
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
