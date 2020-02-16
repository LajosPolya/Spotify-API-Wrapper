package com.lajospolya.spotifyapiwrapper.client;

import com.lajospolya.spotifyapiwrapper.reflection.IReflectiveSpotifyClientService;
import com.lajospolya.spotifyapiwrapper.response.AuthorizationResponse;
import com.lajospolya.spotifyapiwrapper.spotifyexception.SpotifyRequestAuthorizationException;
import com.lajospolya.spotifyapiwrapper.spotifyrequest.AbstractSpotifyRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SpotifyApiClientTest
{
    private SpotifyApiClient spotifyApiClient = new SpotifyApiClient();

    private IReflectiveSpotifyClientService reflectiveSpotifyClientService = mock(IReflectiveSpotifyClientService.class);
    private ISpotifyApiClientService spotifyApiClientService = mock(ISpotifyApiClientService.class);
    private AbstractSpotifyRequest<?> request = mock(AbstractSpotifyRequest.class);
    private AuthorizationResponse authorizationResponse = mock(AuthorizationResponse.class);

    @Test
    void verify_sendRequest_throwsExceptionWhenTokenHasExpired()
    {
        spotifyApiClient.spotifyApiClientService = spotifyApiClientService;
        spotifyApiClient.apiTokenResponse = authorizationResponse;

        when(spotifyApiClientService.hasTokenExpired(nullable(Long.class), nullable(Integer.class))).thenReturn(true);

        SpotifyRequestAuthorizationException e = assertThrows(SpotifyRequestAuthorizationException.class, () ->
        {
            spotifyApiClient.sendRequest(request);
        });

        assertEquals(e.getMessage(), "Access Token Has Expired");
    }
}