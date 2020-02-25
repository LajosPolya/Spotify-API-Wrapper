package com.lajospolya.spotifyapiwrapper.response;

import com.lajospolya.spotifyapiwrapper.enumeration.RecommendationSeedType;

public class RecommendationSeed
{
    private Integer afterFilteringSize;
    private Integer afterRelinkingSize;
    private String href;
    private String id;
    private Integer initialPoolSize;
    private RecommendationSeedType type;

    public Integer getAfterFilteringSize()
    {
        return afterFilteringSize;
    }

    public void setAfterFilteringSize(Integer afterFilteringSize)
    {
        this.afterFilteringSize = afterFilteringSize;
    }

    public Integer getAfterRelinkingSize()
    {
        return afterRelinkingSize;
    }

    public void setAfterRelinkingSize(Integer afterRelinkingSize)
    {
        this.afterRelinkingSize = afterRelinkingSize;
    }

    public String getHref()
    {
        return href;
    }

    public void setHref(String href)
    {
        this.href = href;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public Integer getInitialPoolSize()
    {
        return initialPoolSize;
    }

    public void setInitialPoolSize(Integer initialPoolSize)
    {
        this.initialPoolSize = initialPoolSize;
    }

    public RecommendationSeedType getType()
    {
        return type;
    }

    public void setType(RecommendationSeedType type)
    {
        this.type = type;
    }
}
