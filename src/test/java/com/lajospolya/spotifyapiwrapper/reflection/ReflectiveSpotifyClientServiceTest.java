package com.lajospolya.spotifyapiwrapper.reflection;

import com.lajospolya.spotifyapiwrapper.request.AbstractSpotifyRequest;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ReflectiveSpotifyClientServiceTest
{
    MethodWrapper setAccessToken = mock(MethodWrapper.class);
    MethodWrapper buildRequest = mock(MethodWrapper.class);
    AbstractSpotifyRequest<?> request = mock(AbstractSpotifyRequest.class);

    ReflectiveSpotifyClientService reflectiveSpotifyClientService = new ReflectiveSpotifyClientService(setAccessToken, buildRequest);

    @Test
    void verify_setAccessTokenOfRequest_invokesCorrectMethodWithAppropriateAccessibility() throws InvocationTargetException, IllegalAccessException
    {
        String token = "aToken";

        reflectiveSpotifyClientService.setAccessTokenOfRequest(request, token);

        verify(setAccessToken).setAccessible(true);
        verify(setAccessToken).invoke(same(request), same(token));
        verify(setAccessToken).setAccessible(false);
    }

    @Test
    void verify_buildRequest_invokesCorrectMethodWithAppropriateAccessibility() throws InvocationTargetException, IllegalAccessException
    {
        reflectiveSpotifyClientService.buildRequest(request);

        verify(buildRequest).setAccessible(true);
        verify(buildRequest).invoke(same(request), isNull());
        verify(buildRequest).setAccessible(false);
    }
}