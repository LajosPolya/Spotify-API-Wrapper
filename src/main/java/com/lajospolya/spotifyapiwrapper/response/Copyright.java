package com.lajospolya.spotifyapiwrapper.response;

import com.lajospolya.spotifyapiwrapper.enumeration.CopyrightType;

/**
 * @author Lajos Polya
 * Represent a Copyright object as described at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
public class Copyright
{
    private String text;
    private CopyrightType type;

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public CopyrightType getType()
    {
        return type;
    }

    public void setType(CopyrightType type)
    {
        this.type = type;
    }
}
