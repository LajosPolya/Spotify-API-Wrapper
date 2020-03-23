package com.lajospolya.spotifyapiwrapper.reflection;

import com.lajospolya.spotifyapiwrapper.request.AbstractSpotifyRequest;

import java.lang.reflect.*;
import java.net.http.HttpRequest;

public interface IReflectiveSpotifyClientService
{
    void setAccessTokenOfRequest(AbstractSpotifyRequest<?> spotifyRequest, String accessToken) throws IllegalAccessException, InvocationTargetException;

    HttpRequest buildRequest(AbstractSpotifyRequest<?> spotifyRequest) throws InvocationTargetException, IllegalAccessException;

    Type getParameterizedTypeOfRequest(AbstractSpotifyRequest<?> spotifyRequest);
}
