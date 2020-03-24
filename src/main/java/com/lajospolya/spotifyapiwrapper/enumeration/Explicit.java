package com.lajospolya.spotifyapiwrapper.enumeration;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.lajospolya.spotifyapiwrapper.enumeration.adapter.ExplicitAdapter;

@JsonAdapter(ExplicitAdapter.class)
public enum Explicit
{
    @SerializedName("true")
    TRUE("true"),
    @SerializedName("false")
    FALSE("false"),
    @SerializedName("unknown")
    UNKNOWN("unknown");

    private String type;

    Explicit(String type)
    {
        this.type = type;
    }

    public String getType()
    {
        return type;
    }

    public static Explicit getFromString(String type)
    {
        if(TRUE.type.equals(type))
        {
            return TRUE;
        }
        else if(FALSE.type.equals(type))
        {
            return FALSE;
        }
        return UNKNOWN;
    }
}
