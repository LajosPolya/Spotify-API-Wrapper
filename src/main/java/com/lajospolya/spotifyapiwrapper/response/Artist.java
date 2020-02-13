package com.lajospolya.spotifyapiwrapper.response;

import java.util.List;

public class Artist extends SimplifiedArtist
{
    private Follower follower;
    private List<String> genres;
    private List<Image> images;
    private Integer popularity;

    public Follower getFollower()
    {
        return follower;
    }

    public void setFollower(Follower follower)
    {
        this.follower = follower;
    }

    public List<String> getGenres()
    {
        return genres;
    }

    public void setGenres(List<String> genres)
    {
        this.genres = genres;
    }

    public List<Image> getImages()
    {
        return images;
    }

    public void setImages(List<Image> images)
    {
        this.images = images;
    }

    public Integer getPopularity()
    {
        return popularity;
    }

    public void setPopularity(Integer popularity)
    {
        this.popularity = popularity;
    }
}
