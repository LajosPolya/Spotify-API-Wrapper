package com.lajospolya.spotifyapiwrapper.reflection;

import com.lajospolya.spotifyapiwrapper.spotifyexception.SpotifyBadClassDefinitionException;
import com.lajospolya.spotifyapiwrapper.spotifyrequest.AbstractSpotifyRequest;

import java.lang.reflect.*;
import java.net.http.HttpRequest;

public class ReflectiveSpotifyClientService implements IReflectiveSpotifyClientService
{
    private static final String SET_ACCESS_TOKEN_METHOD_NAME = "reflectiveSetAccessToken";
    private static final String BUILD_REQUEST_METHOD_NAME = "reflectiveBuildRequest";
    private static final Class<?> ABSTRACT_SPOTIFY_REQUEST_CLASS = AbstractSpotifyRequest.class;
    private static final Method setAccessTokenMethod;
    private static final Method buildRequestMethod;

    static
    {
        try
        {
            setAccessTokenMethod = ABSTRACT_SPOTIFY_REQUEST_CLASS.getDeclaredMethod(SET_ACCESS_TOKEN_METHOD_NAME, String.class);
            buildRequestMethod = ABSTRACT_SPOTIFY_REQUEST_CLASS.getDeclaredMethod(BUILD_REQUEST_METHOD_NAME, (Class<?>[]) null);
        }
        catch (NoSuchMethodException e)
        {
            throw new SpotifyBadClassDefinitionException(ABSTRACT_SPOTIFY_REQUEST_CLASS + " does not contain one of the methods needed to send a request");
        }
    }

    public <T> void setAccessTokenOfRequest(AbstractSpotifyRequest<T> spotifyRequest, String accessToken) throws IllegalAccessException, InvocationTargetException
    {
        setAccessTokenMethod.setAccessible(true);
        setAccessTokenMethod.invoke(spotifyRequest, accessToken);
        setAccessTokenMethod.setAccessible(false);
    }

    public <T> HttpRequest buildRequest(AbstractSpotifyRequest<T> spotifyRequest) throws InvocationTargetException, IllegalAccessException
    {
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
