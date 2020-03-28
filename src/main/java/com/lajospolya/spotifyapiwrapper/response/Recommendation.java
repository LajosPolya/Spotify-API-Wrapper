package com.lajospolya.spotifyapiwrapper.response;

import java.util.List;

/**
 * @author Lajos Polya
 * Represent the response of GetRecommendations as described at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
public class Recommendation
{
    private List<RecommendationSeed> seeds;
    private List<SimplifiedTrack> tracks;

    public List<RecommendationSeed> getSeeds()
    {
        return seeds;
    }

    public void setSeeds(List<RecommendationSeed> seeds)
    {
        this.seeds = seeds;
    }

    public List<SimplifiedTrack> getTracks()
    {
        return tracks;
    }

    public void setTracks(List<SimplifiedTrack> tracks)
    {
        this.tracks = tracks;
    }
}
