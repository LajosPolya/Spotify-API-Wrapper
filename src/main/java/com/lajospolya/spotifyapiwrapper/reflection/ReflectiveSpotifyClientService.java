package com.lajospolya.spotifyapiwrapper.reflection;

import com.lajospolya.spotifyapiwrapper.request.AbstractSpotifyRequest;
import com.lajospolya.spotifyapiwrapper.component.ISpotifyRequest;
import com.lajospolya.spotifyapiwrapper.spotifyexception.SpotifyBadClassDefinitionException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

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

    @Override
    public void setAccessTokenOfRequest(AbstractSpotifyRequest<?> spotifyRequest, String accessToken) throws IllegalAccessException, InvocationTargetException
    {
        setAccessTokenMethod.setAccessible(true);
        setAccessTokenMethod.invoke(spotifyRequest, accessToken);
        setAccessTokenMethod.setAccessible(false);
    }

    @Override
    public <T, U> ISpotifyRequest<U> buildRequest(AbstractSpotifyRequest<T> spotifyRequest) throws InvocationTargetException, IllegalAccessException
    {
        buildRequestMethod.setAccessible(true);
        ISpotifyRequest<U> request =  (ISpotifyRequest<U>) buildRequestMethod.invoke(spotifyRequest, (Object[]) null);
        buildRequestMethod.setAccessible(false);
        return request;
    }

    @Override
    public Type getParameterizedTypeOfRequest(AbstractSpotifyRequest<?> spotifyRequest)
    {
        return ((ParameterizedType)spotifyRequest.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}
