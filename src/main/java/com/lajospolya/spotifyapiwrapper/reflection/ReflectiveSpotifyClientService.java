package com.lajospolya.spotifyapiwrapper.reflection;

import com.lajospolya.spotifyapiwrapper.spotifyrequest.AbstractSpotifyRequest;

import java.lang.reflect.*;
import java.net.http.HttpRequest;

public class ReflectiveSpotifyClientService implements IReflectiveSpotifyClientService
{
    private static final String TOKEN_FIELD_NAME = "accessToken";
    private static final String BUILD_REQUEST_METHOD_NAME = "buildRequest";

    public <T> void setAccessTokenOfRequest(AbstractSpotifyRequest<T> spotifyRequest, String accessToken) throws NoSuchFieldException, IllegalAccessException
    {
        Class<?> runtimeRequest = spotifyRequest.getClass();
        Field accessTokenField = runtimeRequest.getDeclaredField(TOKEN_FIELD_NAME);
        accessTokenField.setAccessible(true);
        accessTokenField.set(spotifyRequest, accessToken);
    }

    public <T> HttpRequest buildRequest(AbstractSpotifyRequest<T> spotifyRequest) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException
    {
        Class<?> requestType = spotifyRequest.getClass();
        Method buildRequest = requestType.getDeclaredMethod(BUILD_REQUEST_METHOD_NAME, (Class<?>[]) null);

        buildRequest.setAccessible(true);
        return (HttpRequest) buildRequest.invoke(spotifyRequest, (Object[]) null);
    }

    public <T> Type getGenericTypeOfRequest(AbstractSpotifyRequest<T> spotifyRequest)
    {

        return ((ParameterizedType)spotifyRequest.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}
