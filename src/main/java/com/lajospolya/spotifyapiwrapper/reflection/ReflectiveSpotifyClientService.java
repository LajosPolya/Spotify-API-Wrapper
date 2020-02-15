package com.lajospolya.spotifyapiwrapper.reflection;

import com.lajospolya.spotifyapiwrapper.spotifyrequest.AbstractSpotifyRequest;

import java.lang.reflect.*;
import java.net.http.HttpRequest;

public class ReflectiveSpotifyClientService implements IReflectiveSpotifyClientService
{
    private static final String TOKEN_FIELD_NAME = "setAccessToken";
    private static final String BUILD_REQUEST_METHOD_NAME = "buildRequest";

    public <T> void setAccessTokenOfRequest(AbstractSpotifyRequest<T> spotifyRequest, String accessToken) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
    {
        Class<?> requestClassType = spotifyRequest.getClass();
        Method setAccessTokenMethod = requestClassType.getDeclaredMethod(TOKEN_FIELD_NAME, String.class);
        setAccessTokenMethod.setAccessible(true);
        setAccessTokenMethod.invoke(spotifyRequest, accessToken);
        setAccessTokenMethod.setAccessible(false);
    }

    public <T> HttpRequest buildRequest(AbstractSpotifyRequest<T> spotifyRequest) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException
    {
        Class<?> requestClassType = spotifyRequest.getClass();
        Method buildRequestMethod = requestClassType.getDeclaredMethod(BUILD_REQUEST_METHOD_NAME, (Class<?>[]) null);

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
