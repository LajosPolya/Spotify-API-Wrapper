package com.lajospolya.spotifyapiwrapper.spotifyrequest;

// This must be an abstract class in order to get the Generic type later
public abstract class SpotifyRequest<T>
{
    static final String SPOTIFY_V1_API_URI = "https://api.spotify.com/v1/";
    static final String AUTHORIZATION_HEADER = "Authorization";
    static final String IDS_QUERY_PARAM = "ids";
}
