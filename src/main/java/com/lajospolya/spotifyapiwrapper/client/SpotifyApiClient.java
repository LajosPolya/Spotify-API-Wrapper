package com.lajospolya.spotifyapiwrapper.client;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.lajospolya.spotifyapiwrapper.authorization.AuthorizationResponse;
import com.lajospolya.spotifyapiwrapper.client.response.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.lang.reflect.Type;
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

    // Artists API
    private static final String GET_ARTIST = SPOTIFY_V1_API_URI + "artists/";
    private static final String GET_ARTISTS = SPOTIFY_V1_API_URI + "artists";
    private static final String GET_ARTISTS_ALBUMS = SPOTIFY_V1_API_URI + "artists/{id}/albums";
    private static final String GET_ARTISTS_TOP_TRACKS = SPOTIFY_V1_API_URI + "artists/{id}/top-tracks";
    private static final String GET_ARTISTS_RELATED_ARTISTS = SPOTIFY_V1_API_URI + "artists/{id}/related-artists";

    // Tracks API
    private static final String GET_TRACKS = SPOTIFY_V1_API_URI + "tracks";
    private static final String GET_TRACK = SPOTIFY_V1_API_URI + "tracks/";
    private static final String GET_TRACK_AUDIO_FEATURES = SPOTIFY_V1_API_URI + "audio-features/";
    private static final String GET_TRACKS_AUDIO_FEATURES = SPOTIFY_V1_API_URI + "audio-features";
    private static final String GET_TRACKS_AUDIO_ANALYSIS = SPOTIFY_V1_API_URI + "audio-analysis/";

    // Albums API
    private static final String GET_ALBUMS = SPOTIFY_V1_API_URI + "albums";
    private static final String GET_ALBUM = SPOTIFY_V1_API_URI + "albums/";
    private static final String GET_ALBUM_TRACKS = SPOTIFY_V1_API_URI + "albums/{id}/tracks";

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
        HttpRequest getArtistRequest = HttpRequest.newBuilder()
                .uri(URI.create(GET_ARTIST + artistId))
                .header(AUTHORIZATION_HEADER, this.builtToken)
                .GET()
                .build();

        try
        {
            return sendRequestAndFetchResponse(getArtistRequest, Artist.class);
        }
        catch (InterruptedException | IOException e)
        {
            throw new RuntimeException("Unable to fetch Artist");
        }
        catch (JsonSyntaxException e)
        {
            System.out.println(e.getMessage());
            throw new RuntimeException("Unable to serialize Artist");
        }
    }

    public List<Artist> getArtists(List<String> artistIds)
    {
        String commaSeparatedIds = String.join(",", artistIds);
        UriComponentsBuilder artistsBuilder =  UriComponentsBuilder.fromUriString(GET_ARTISTS);
        artistsBuilder.queryParam("ids", commaSeparatedIds);

        HttpRequest getArtistsRequest = HttpRequest.newBuilder()
                .uri(artistsBuilder.build().toUri())
                .header(AUTHORIZATION_HEADER, this.builtToken)
                .GET()
                .build();

        try
        {
            return sendRequestAndFetchResponse(getArtistsRequest, Artists.class).getArtists();
        }
        catch (InterruptedException | IOException e)
        {
            throw new RuntimeException("Unable to fetch Artists");
        }
        catch (JsonSyntaxException e)
        {
            System.out.println(e.getMessage());
            throw new RuntimeException("Unable to serialize Artists" + e.getMessage(), e);
        }
    }

    public List<SimplifiedAlbum> getArtistsAlbums(String artistId)
    {
        UriComponentsBuilder artistsAlbumsBuilder =  UriComponentsBuilder.fromUriString(GET_ARTISTS_ALBUMS);

        HttpRequest getArtistsAlbumsRequest = HttpRequest.newBuilder()
                .uri(artistsAlbumsBuilder.buildAndExpand(artistId).toUri())
                .header(AUTHORIZATION_HEADER, this.builtToken)
                .GET()
                .build();

        try
        {
            return sendRequestAndFetchResponse(getArtistsAlbumsRequest, ArtistsAlbums.class).getItems();
        }
        catch (InterruptedException | IOException e)
        {
            throw new RuntimeException("Unable to fetch Artist's Albums");
        }
        catch (JsonSyntaxException e)
        {
            System.out.println(e.getMessage());
            throw new RuntimeException("Unable to serialize Artist's Albums" + e.getMessage(), e);
        }
    }

    public List<Track> getArtistsTopTracks(String artistId)
    {
        UriComponentsBuilder artistsTopTracksBuilder =  UriComponentsBuilder.fromUriString(GET_ARTISTS_TOP_TRACKS);
        artistsTopTracksBuilder.queryParam("market", "CA");

        HttpRequest getArtistsTopTracksRequest = HttpRequest.newBuilder()
                .uri(artistsTopTracksBuilder.buildAndExpand(artistId).toUri())
                .header(AUTHORIZATION_HEADER, this.builtToken)
                .GET()
                .build();

        try
        {
            return sendRequestAndFetchResponse(getArtistsTopTracksRequest, ArtistsTopTracks.class).getTracks();
        }
        catch (InterruptedException | IOException e)
        {
            throw new RuntimeException("Unable to fetch Artist's Top Tracks");
        }
        catch (JsonSyntaxException e)
        {
            System.out.println(e.getMessage());
            throw new RuntimeException("Unable to serialize Artist's Top Tracks" + e.getMessage(), e);
        }
    }

    public List<Artist> getArtistsRelatedArtists(String artistId)
    {
        UriComponentsBuilder artistsRelatedArtistsBuilder =  UriComponentsBuilder.fromUriString(GET_ARTISTS_RELATED_ARTISTS);

        HttpRequest getArtistsRelatedArtistsRequest = HttpRequest.newBuilder()
                .uri(artistsRelatedArtistsBuilder.buildAndExpand(artistId).toUri())
                .header(AUTHORIZATION_HEADER, this.builtToken)
                .GET()
                .build();

        try
        {
            return sendRequestAndFetchResponse(getArtistsRelatedArtistsRequest, Artists.class).getArtists();
        }
        catch (InterruptedException | IOException e)
        {
            throw new RuntimeException("Unable to fetch Artist's Related Artists");
        }
        catch (JsonSyntaxException e)
        {
            System.out.println(e.getMessage());
            throw new RuntimeException("Unable to serialize Artist's Related Artists" + e.getMessage(), e);
        }
    }

    public List<Track> getTracks(List<String> trackIds)
    {
        String commaSeparatedIds = String.join(",", trackIds);
        UriComponentsBuilder tracksBuilder =  UriComponentsBuilder.fromUriString(GET_TRACKS);
        tracksBuilder.queryParam("ids", commaSeparatedIds);

        HttpRequest getTracksRequest = HttpRequest.newBuilder()
                .uri(tracksBuilder.build().toUri())
                .header(AUTHORIZATION_HEADER, this.builtToken)
                .GET()
                .build();

        try
        {
            return sendRequestAndFetchResponse(getTracksRequest, Tracks.class).getTracks();
        }
        catch (InterruptedException | IOException e)
        {
            throw new RuntimeException("Unable to fetch tracks");
        }
        catch (JsonSyntaxException e)
        {
            System.out.println(e.getMessage());
            throw new RuntimeException("Unable to serialize tracks" + e.getMessage(), e);
        }
    }

    public Track getTrack(String trackId)
    {
        UriComponentsBuilder trackBuilder =  UriComponentsBuilder.fromUriString(GET_TRACK + trackId);

        HttpRequest getTrackRequest = HttpRequest.newBuilder()
                .uri(trackBuilder.build().toUri())
                .header(AUTHORIZATION_HEADER, this.builtToken)
                .GET()
                .build();

        try
        {
            return sendRequestAndFetchResponse(getTrackRequest, Track.class);
        }
        catch (InterruptedException | IOException e)
        {
            throw new RuntimeException("Unable to fetch track");
        }
        catch (JsonSyntaxException e)
        {
            System.out.println(e.getMessage());
            throw new RuntimeException("Unable to serialize artist" + e.getMessage(), e);
        }
    }

    public AudioFeatures getAudioFeatures(String trackId)
    {
        UriComponentsBuilder audioFeaturesBuilder =  UriComponentsBuilder.fromUriString(GET_TRACK_AUDIO_FEATURES + trackId);

        HttpRequest getAudioFeaturesRequest = HttpRequest.newBuilder()
                .uri(audioFeaturesBuilder.build().toUri())
                .header(AUTHORIZATION_HEADER, this.builtToken)
                .GET()
                .build();

        try
        {
            return sendRequestAndFetchResponse(getAudioFeaturesRequest, AudioFeatures.class);
        }
        catch (InterruptedException | IOException e)
        {
            throw new RuntimeException("Unable to fetch Audio Features");
        }
        catch (JsonSyntaxException e)
        {
            System.out.println(e.getMessage());
            throw new RuntimeException("Unable to serialize Audio Features " + e.getMessage(), e);
        }
    }

    public List<AudioFeatures> getAudioFeatures(List<String> trackIds)
    {
        String commaSeparatedTrackIds = String.join(",", trackIds);
        UriComponentsBuilder tracksAudioFeaturesBuilder =  UriComponentsBuilder.fromUriString(GET_TRACKS_AUDIO_FEATURES);
        tracksAudioFeaturesBuilder.queryParam("ids", commaSeparatedTrackIds);

        HttpRequest getTracksAudioFeaturesRequest = HttpRequest.newBuilder()
                .uri(tracksAudioFeaturesBuilder.build().toUri())
                .header(AUTHORIZATION_HEADER, this.builtToken)
                .GET()
                .build();

        try
        {
            return sendRequestAndFetchResponse(getTracksAudioFeaturesRequest, TracksAudioFeatures.class).getAudio_features();
        }
        catch (InterruptedException | IOException e)
        {
            throw new RuntimeException("Unable to fetch several Audio Features");
        }
        catch (JsonSyntaxException e)
        {
            System.out.println(e.getMessage());
            throw new RuntimeException("Unable to serialize several Audio Features" + e.getMessage(), e);
        }
    }

    public String getAudioAnalysis(String trackId)
    {
        UriComponentsBuilder tracksAudioAnalysisBuilder =  UriComponentsBuilder.fromUriString(GET_TRACKS_AUDIO_ANALYSIS + trackId);

        HttpRequest getTracksAudioAnalysisRequest = HttpRequest.newBuilder()
                .uri(tracksAudioAnalysisBuilder.build().toUri())
                .header(AUTHORIZATION_HEADER, this.builtToken)
                .GET()
                .build();

        try
        {
            HttpResponse<String> resp = httpClient.send(getTracksAudioAnalysisRequest, HttpResponse.BodyHandlers.ofString());
            String body = resp.body();
            return body;
        }
        catch (InterruptedException | IOException e)
        {
            throw new RuntimeException("Unable to fetch Audio Analysis");
        }
        catch (JsonSyntaxException e)
        {
            System.out.println(e.getMessage());
            throw new RuntimeException("Unable to serialize Audio Analysis" + e.getMessage(), e);
        }
    }

    public List<Album> getAlbums(List<String> albumIds)
    {
        String commaSeparatedIds = String.join(",", albumIds);
        UriComponentsBuilder albumsBuilder =  UriComponentsBuilder.fromUriString(GET_ALBUMS);
        albumsBuilder.queryParam("ids", commaSeparatedIds);

        HttpRequest getAlbumsRequest = HttpRequest.newBuilder()
                .uri(albumsBuilder.build().toUri())
                .header(AUTHORIZATION_HEADER, this.builtToken)
                .GET()
                .build();

        try
        {
            return sendRequestAndFetchResponse(getAlbumsRequest, Albums.class).getAlbums();
        }
        catch (InterruptedException | IOException e)
        {
            throw new RuntimeException("Unable to fetch albums");
        }
        catch (JsonSyntaxException e)
        {
            System.out.println(e.getMessage());
            throw new RuntimeException("Unable to serialize albums" + e.getMessage(), e);
        }
    }

    public Album getAlbum(String albumId)
    {
        UriComponentsBuilder albumsBuilder =  UriComponentsBuilder.fromUriString(GET_ALBUM + albumId);

        HttpRequest getAlbumRequest = HttpRequest.newBuilder()
                .uri(albumsBuilder.build().toUri())
                .header(AUTHORIZATION_HEADER, this.builtToken)
                .GET()
                .build();

        try
        {
            return sendRequestAndFetchResponse(getAlbumRequest, Album.class);
        }
        catch (InterruptedException | IOException e)
        {
            throw new RuntimeException("Unable to fetch album");
        }
        catch (JsonSyntaxException e)
        {
            System.out.println(e.getMessage());
            throw new RuntimeException("Unable to serialize album" + e.getMessage(), e);
        }
    }

    public Paging<SimplifiedTrack> getAlbumTracks(String albumId)
    {
        UriComponentsBuilder albumsBuilder =  UriComponentsBuilder.fromUriString(GET_ALBUM_TRACKS);

        HttpRequest getAlbumsRequest = HttpRequest.newBuilder()
                .uri(albumsBuilder.buildAndExpand(albumId).toUri())
                .header(AUTHORIZATION_HEADER, this.builtToken)
                .GET()
                .build();

        try
        {
            Type collectionType = new TypeToken<Paging<SimplifiedTrack>>(){}.getType();
            return sendRequestAndFetchResponse(getAlbumsRequest, collectionType);
        }
        catch (InterruptedException | IOException e)
        {
            throw new RuntimeException("Unable to fetch Album's Tracks");
        }
        catch (JsonSyntaxException e)
        {
            System.out.println(e.getMessage());
            throw new RuntimeException("Unable to serialize Album's Tracks" + e.getMessage(), e);
        }
    }

    private <T> T sendRequestAndFetchResponse(HttpRequest request, Class<T> dtoClass) throws IOException, InterruptedException
    {
        HttpResponse<String> resp = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        String body = resp.body();

        return gson.fromJson(body, dtoClass);
    }

    private <T> T sendRequestAndFetchResponse(HttpRequest request, Type collectionType) throws IOException, InterruptedException
    {
        HttpResponse<String> resp = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        String body = resp.body();

        return gson.fromJson(body, collectionType);
    }
}
