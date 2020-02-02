package com.lajospolya.spotifyapiwrapper.client;

import com.lajospolya.spotifyapiwrapper.authorization.AuthorizationResponse;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class SpotifyApiClient
{
    private HttpClient httpClient;
    private AuthorizationResponse apiTokenResponse;

    private static String URL = "https://api.spotify.com/v1/tracks";

    public SpotifyApiClient(AuthorizationResponse authorizationResponse)
    {
        this.apiTokenResponse = authorizationResponse;
        this.httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();
    }

    public void getArtist()
    {
        HttpRequest getTracksRequest = HttpRequest.newBuilder()
                .uri(URI.create(URL + "?ids=5FVd6KXrgO9B3JPmC8OPst"))
                .header("Authorization", apiTokenResponse.getTokenType() + " " + apiTokenResponse.getAccessToken())
                .GET()
                .build();

        try
        {
            HttpResponse<String> resp = httpClient.send(getTracksRequest, HttpResponse.BodyHandlers.ofString());
            String body = resp.body();
        }
        catch (InterruptedException | IOException e)
        {
            throw new RuntimeException("Unable to fetch tracks");
        }
    }
}
