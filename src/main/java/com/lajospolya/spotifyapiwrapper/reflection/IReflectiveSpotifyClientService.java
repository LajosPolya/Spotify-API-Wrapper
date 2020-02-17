package com.lajospolya.spotifyapiwrapper.reflection;

import com.lajospolya.spotifyapiwrapper.spotifyrequest.AbstractSpotifyRequest;

import java.lang.reflect.*;
import java.net.http.HttpRequest;

public interface IReflectiveSpotifyClientService
{
    <T> void setAccessTokenOfRequest(AbstractSpotifyRequest<T> spotifyRequest, String accessToken) throws IllegalAccessException, InvocationTargetException;

    <T> HttpRequest buildRequest(AbstractSpotifyRequest<T> spotifyRequest) throws InvocationTargetException, IllegalAccessException;

    <T> Type getGenericTypeOfRequest(AbstractSpotifyRequest<T> spotifyRequest);
}
