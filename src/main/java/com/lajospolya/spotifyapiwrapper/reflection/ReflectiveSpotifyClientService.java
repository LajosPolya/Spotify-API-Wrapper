package com.lajospolya.spotifyapiwrapper.reflection;

import com.lajospolya.spotifyapiwrapper.spotifyexception.SpotifyBadClassDefinitionException;
import com.lajospolya.spotifyapiwrapper.spotifyrequest.AbstractSpotifyRequest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.http.HttpRequest;

public class ReflectiveSpotifyClientService implements IReflectiveSpotifyClientService
{
    private static final String SET_ACCESS_TOKEN_METHOD_NAME = "reflectiveSetAccessToken";
    private static final String BUILD_REQUEST_METHOD_NAME = "reflectiveBuildRequest";
    private static final Class<?> ABSTRACT_SPOTIFY_REQUEST_CLASS = AbstractSpotifyRequest.class;
    private final MethodWrapper setAccessTokenMethod;
    private final MethodWrapper buildRequestMethod;

    // Constructor for testing
    ReflectiveSpotifyClientService(MethodWrapper setAccessTokenMethod, MethodWrapper buildRequestMethod)
    {
        this.setAccessTokenMethod = setAccessTokenMethod;
        this.buildRequestMethod = buildRequestMethod;
    }

    public ReflectiveSpotifyClientService()
    {
        try
        {
            this.setAccessTokenMethod = new MethodWrapper(ABSTRACT_SPOTIFY_REQUEST_CLASS.getDeclaredMethod(SET_ACCESS_TOKEN_METHOD_NAME, String.class));
            this.buildRequestMethod = new MethodWrapper(ABSTRACT_SPOTIFY_REQUEST_CLASS.getDeclaredMethod(BUILD_REQUEST_METHOD_NAME, (Class<?>[]) null));
        }
        catch (NoSuchMethodException e)
        {
            throw new SpotifyBadClassDefinitionException("Some of the mandatory methods weren't found on " + ABSTRACT_SPOTIFY_REQUEST_CLASS);
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
