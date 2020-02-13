package com.lajospolya.spotifyapiwrapper.reflection;

import com.lajospolya.spotifyapiwrapper.spotifyrequest.AbstractSpotifyRequest;

import java.lang.reflect.*;
import java.net.http.HttpRequest;

public interface IReflectiveSpotifyClientService
{
    <T> void setAccessTokenOfRequest(AbstractSpotifyRequest<T> spotifyRequest, String accessToken) throws NoSuchFieldException, IllegalAccessException;

    <T> HttpRequest buildRequest(AbstractSpotifyRequest<T> spotifyRequest) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;

    <T> Type getGenericTypeOfRequest(AbstractSpotifyRequest<T> spotifyRequest);
}
