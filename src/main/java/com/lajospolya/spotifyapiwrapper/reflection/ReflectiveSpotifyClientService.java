package com.lajospolya.spotifyapiwrapper.reflection;

import com.lajospolya.spotifyapiwrapper.spotifyrequest.AbstractSpotifyRequest;

import java.lang.reflect.*;
import java.net.http.HttpRequest;

public class ReflectiveSpotifyClientService implements IReflectiveSpotifyClientService
{
    private static final String SET_ACCESS_TOKEN_METHOD_NAME = "setAccessToken";
    private static final String BUILD_REQUEST_METHOD_NAME = "buildRequest";
    private static final Class<?> ABSTRACT_SPOTIFY_REQUEST_CLASS = AbstractSpotifyRequest.class;

    public <T> void setAccessTokenOfRequest(AbstractSpotifyRequest<T> spotifyRequest, String accessToken) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
    {
        // Would it beneficial for this Method to be static? What should I do in the exceptional case in static block initialization
        Method setAccessTokenMethod = ABSTRACT_SPOTIFY_REQUEST_CLASS.getDeclaredMethod(SET_ACCESS_TOKEN_METHOD_NAME, String.class);
        setAccessTokenMethod.setAccessible(true);
        setAccessTokenMethod.invoke(spotifyRequest, accessToken);
        setAccessTokenMethod.setAccessible(false);
    }

    public <T> HttpRequest buildRequest(AbstractSpotifyRequest<T> spotifyRequest) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException
    {
        Method buildRequestMethod = ABSTRACT_SPOTIFY_REQUEST_CLASS.getDeclaredMethod(BUILD_REQUEST_METHOD_NAME, (Class<?>[]) null);
        buildRequestMethod.setAccessible(true);
        HttpRequest request =  (HttpRequest) buildRequestMethod.invoke(spotifyRequest, (Object[]) null);
        buildRequestMethod.setAccessible(false);
        return request;
    }

    public <T> Type getGenericTypeOfRequest(AbstractSpotifyRequest<T> spotifyRequest)
    {
        return ((ParameterizedType)spotifyRequest.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}
