package com.lajospolya.spotifyapiwrapper.body;

import com.google.gson.annotations.SerializedName;

public class PlaylistDetails
{
    private String name;
    @SerializedName("public")
    private Boolean isPublic;
    private Boolean collaborative;
    private String description;

    public PlaylistDetails(String name, Boolean isPublic, Boolean collaborative, String description)
    {
        this.name = name;
        this.isPublic = isPublic;
        this.collaborative = collaborative;
        this.description = description;
    }
}
