package com.lajospolya.spotifyapiwrapper.component;

import com.lajospolya.spotifyapiwrapper.response.SpotifyErrorContainer;

public interface ISpotifyResponse<ResponseType>
{
    ResponseType body();

    SpotifyErrorContainer error();
}
