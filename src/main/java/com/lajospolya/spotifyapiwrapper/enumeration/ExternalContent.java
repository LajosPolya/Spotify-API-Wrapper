package com.lajospolya.spotifyapiwrapper.enumeration;

public enum ExternalContent
{
    Audio("audio");

    ExternalContent(String content)
    {

        this.content = content;
    }

    private final String content;

    public String getContent()
    {
        return content;
    }
}
