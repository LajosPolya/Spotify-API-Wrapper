package com.lajospolya.spotifyapiwrapper.client;

import com.lajospolya.spotifyapiwrapper.client.service.ISpotifyApiClientService;
import com.lajospolya.spotifyapiwrapper.reflection.IReflectiveSpotifyClientService;
import com.lajospolya.spotifyapiwrapper.response.AuthorizingToken;
import com.lajospolya.spotifyapiwrapper.spotifyexception.SpotifyRequestAuthorizationException;
import com.lajospolya.spotifyapiwrapper.request.AbstractSpotifyRequest;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.net.http.HttpRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.*;

class SpotifyApiClientTest
{
    private IReflectiveSpotifyClientService reflectiveSpotifyClientMock = mock(IReflectiveSpotifyClientService.class);
    private ISpotifyApiClientService spotifyApiClientMock = mock(ISpotifyApiClientService.class);
    private AuthorizingToken authorizingTokenMock = mock(AuthorizingToken.class);
    private AbstractSpotifyRequest<?> requestMock = mock(AbstractSpotifyRequest.class);

    private SpotifyApiClient spotifyApiClient = new SpotifyApiClient(reflectiveSpotifyClientMock, spotifyApiClientMock, authorizingTokenMock);

    @Test
    void verify_sendRequest_throwsExceptionWhenTokenHasExpired()
    {
        when(spotifyApiClientMock.hasTokenExpired(nullable(Long.class), nullable(Integer.class))).thenReturn(true);

        SpotifyRequestAuthorizationException e = assertThrows(SpotifyRequestAuthorizationException.class, () ->
                spotifyApiClient.sendRequest(requestMock));

        assertEquals(e.getMessage(), "Access Token Has Expired");
    }

    @Test
    void verify_sendRequest_buildsAndSendsRequest() throws IllegalAccessException, InvocationTargetException, IOException, InterruptedException
    {
        when(spotifyApiClientMock.hasTokenExpired(nullable(Long.class), nullable(Integer.class))).thenReturn(false);

        spotifyApiClient.sendRequest(requestMock);

        verify(reflectiveSpotifyClientMock).setAccessTokenOfRequest(same(requestMock), nullable(String.class));
        verify(reflectiveSpotifyClientMock).buildRequest(same(requestMock));
        verify(reflectiveSpotifyClientMock).getParameterizedTypeOfRequest(same(requestMock));
        verify(spotifyApiClientMock).sendRequestAndFetchResponse(nullable(HttpRequest.class), nullable(Type.class));
    }
}