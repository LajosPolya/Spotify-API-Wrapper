package com.lajospolya.spotifyapiwrapper.reflection;

import com.lajospolya.spotifyapiwrapper.request.AbstractSpotifyRequest;
import com.lajospolya.spotifyapiwrapper.component.ISpotifyRequest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;

public interface IReflectiveSpotifyClientService
{
    /**
     * Via reflection, sets the private field accessToken on spotifyRequest in order to authorize the request
     * @param spotifyRequest the request to set the access token on
     * @param accessToken the access token
     * @throws IllegalAccessException thrown by the java.lang.reflect method calls
     * @throws InvocationTargetException thrown by the java.lang.reflect method calls
     */
    void setAccessTokenOfRequest(AbstractSpotifyRequest<?> spotifyRequest, String accessToken) throws IllegalAccessException, InvocationTargetException;

    /**
     * Via reflection, calls the private reflectiveBuildRequest method in order to build the request object
     * @param spotifyRequest the request to build
     * @return the built HTTP request
     * @throws InvocationTargetException thrown by the java.lang.reflect method calls
     * @throws IllegalAccessException thrown by the java.lang.reflect method calls
     */
    <T, U> ISpotifyRequest<U> buildRequest(AbstractSpotifyRequest<T> spotifyRequest) throws InvocationTargetException, IllegalAccessException;

    Type getParameterizedTypeOfRequest(AbstractSpotifyRequest<?> spotifyRequest);
}
