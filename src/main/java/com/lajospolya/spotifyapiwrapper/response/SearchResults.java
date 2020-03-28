package com.lajospolya.spotifyapiwrapper.response;

/**
 * @author Lajos Polya
 * Represent the response of GetSearch as described at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
public class SearchResults
{
    private Paging<Artist> artists;
    private Paging<SimplifiedAlbum> albums;
    private Paging<Track> tracks;
    private Paging<Playlist> playlists;

    public Paging<Artist> getArtists()
    {
        return artists;
    }

    public void setArtists(Paging<Artist> artists)
    {
        this.artists = artists;
    }

    public Paging<SimplifiedAlbum> getAlbums()
    {
        return albums;
    }

    public void setAlbums(Paging<SimplifiedAlbum> albums)
    {
        this.albums = albums;
    }

    public Paging<Track> getTracks()
    {
        return tracks;
    }

    public void setTracks(Paging<Track> tracks)
    {
        this.tracks = tracks;
    }

    public Paging<Playlist> getPlaylists()
    {
        return playlists;
    }

    public void setPlaylists(Paging<Playlist> playlists)
    {
        this.playlists = playlists;
    }
}
