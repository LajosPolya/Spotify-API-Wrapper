package com.lajospolya.spotifyapiwrapper.body;

import java.util.List;

public class ResumePlayback
{
    private String context_uri;
    private List<String> uris;
    private Offset offset = null;
    private Integer position_ms;

    public ResumePlayback(String context_uri, List<String> uris, Integer offset, Integer position_ms)
    {
        this.context_uri = context_uri;
        this.uris = uris;
        if (offset != null) {
            this.offset = new Offset(offset);
        }
        this.position_ms = position_ms;
    }
}
