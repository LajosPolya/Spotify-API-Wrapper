package com.lajospolya.spotifyapiwrapper.enumeration;

import com.google.gson.annotations.SerializedName;

public enum CopyrightType
{
    @SerializedName("P")
    P("P"),
    @SerializedName("C")
    C("C");

    CopyrightType(String type)
    {
        this.type = type;
    }

    private String type;

    public String getType()
    {
        return type;
    }
}
