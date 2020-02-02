package com.lajospolya.spotifyapiwrapper.client;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.lajospolya.spotifyapiwrapper.authorization.AuthorizationResponse;
import com.lajospolya.spotifyapiwrapper.client.response.Artist;
import com.lajospolya.spotifyapiwrapper.client.response.Artists;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class SpotifyApiClient
{
    private HttpClient httpClient;
    private AuthorizationResponse apiTokenResponse;
    private String builtToken;
    private Gson gson;

    private static final String AUTHORIZATION_HEADER = "Authorization";

    private static final String SPOTIFY_V1_API_URI = "https://api.spotify.com/v1/";
    private static final String GET_ARTIST = SPOTIFY_V1_API_URI + "artists/";
    private static final String GET_ARTISTS = SPOTIFY_V1_API_URI + "artists";
    private static final String GET_ARTISTS_ALBUMS = SPOTIFY_V1_API_URI + "artists/{id}/albums";

    public SpotifyApiClient(AuthorizationResponse authorizationResponse)
    {
        this.apiTokenResponse = authorizationResponse;
        this.httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();
        this.gson =  new Gson();
        this.builtToken = apiTokenResponse.getTokenType() + " " + apiTokenResponse.getAccessToken();
    }

    public Artist getArtist(String artistId)
    {
        HttpRequest getTracksRequest = HttpRequest.newBuilder()
                .uri(URI.create(GET_ARTIST + artistId))
                .header(AUTHORIZATION_HEADER, this.builtToken)
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

    public List<Artist> getArtists(List<String> artistIds)
    {
        String commaSeparatedIds = String.join(",", artistIds);
        UriComponentsBuilder artistsBuilder =  UriComponentsBuilder.fromUriString(GET_ARTISTS);
        artistsBuilder.queryParam("ids", commaSeparatedIds);

        HttpRequest getTracksRequest = HttpRequest.newBuilder()
                .uri(artistsBuilder.build().toUri())
                .header(AUTHORIZATION_HEADER, this.builtToken)
                .GET()
                .build();

        try
        {
            HttpResponse<String> resp = httpClient.send(getTracksRequest, HttpResponse.BodyHandlers.ofString());
            String body = resp.body();
            Artists artist = gson.fromJson(body, Artists.class);
            return artist.getArtists();
        }
        catch (InterruptedException | IOException e)
        {
            throw new RuntimeException("Unable to fetch tracks");
        }
        catch (JsonSyntaxException e)
        {
            System.out.println(e.getMessage());
            throw new RuntimeException("Unable to serialize artists" + e.getMessage(), e);
        }
    }
}
