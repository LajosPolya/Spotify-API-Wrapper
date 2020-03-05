package com.lajospolya.spotifyapiwrapper.body;

import com.google.gson.annotations.SerializedName;

public final class PutPlaylistsFollowers
{
    @SerializedName("public")
    private Boolean isPublic;

    public PutPlaylistsFollowers(Boolean isPublic)
    {
        this.isPublic = isPublic;
    }
}
