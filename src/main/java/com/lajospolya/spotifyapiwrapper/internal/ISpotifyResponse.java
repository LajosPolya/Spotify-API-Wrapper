package com.lajospolya.spotifyapiwrapper.internal;

import com.lajospolya.spotifyapiwrapper.response.SpotifyErrorContainer;

public interface ISpotifyResponse<ResponseType>
{
    ResponseType body();

    SpotifyErrorContainer error();
}
