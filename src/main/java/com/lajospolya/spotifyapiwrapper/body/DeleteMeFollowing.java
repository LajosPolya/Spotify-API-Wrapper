package com.lajospolya.spotifyapiwrapper.body;

import java.util.List;

public final class DeleteMeFollowing
{
    private List<String> ids;

    public DeleteMeFollowing(List<String> ids)
    {
        this.ids = ids;
    }
}
