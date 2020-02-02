package com.lajospolya.spotifyapiwrapper.client;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.lajospolya.spotifyapiwrapper.authorization.AuthorizationResponse;
import com.lajospolya.spotifyapiwrapper.client.response.Artist;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class SpotifyApiClient
{
    private HttpClient httpClient;
    private AuthorizationResponse apiTokenResponse;
    private Gson gson;

    private static String URL = "https://api.spotify.com/v1/artists/";

    public SpotifyApiClient(AuthorizationResponse authorizationResponse)
    {
        this.apiTokenResponse = authorizationResponse;
        this.httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();
        this.gson =  new Gson();
    }

    public Artist getArtist()
    {
        HttpRequest getTracksRequest = HttpRequest.newBuilder()
                .uri(URI.create(URL + "7Ln80lUS6He07XvHI8qqHH"))
                .header("Authorization", apiTokenResponse.getTokenType() + " " + apiTokenResponse.getAccessToken())
                .GET()
                .build();

        try
        {
            HttpResponse<String> resp = httpClient.send(getTracksRequest, HttpResponse.BodyHandlers.ofString());
            String body = resp.body();
            Artist artist = gson.fromJson(body, Artist.class);
            return artist;
        }
        catch (InterruptedException | IOException e)
        {
            throw new RuntimeException("Unable to fetch tracks");
        }
        catch (JsonSyntaxException e)
        {
            System.out.println(e.getMessage());
            throw new RuntimeException("Unable to serialize tracks");
        }
    }
}
