package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import org.springframework.web.util.UriComponentsBuilder;

import java.net.http.HttpRequest;

public class SpotifyRequestBuilder
{
    private String uri;
    private UriComponentsBuilder uriComponentsBuilder;

    SpotifyRequestBuilder(String uri)
    {
        this.uri = uri;
        this.uriComponentsBuilder = UriComponentsBuilder.fromUriString(uri);
    }

    HttpRequest.Builder createGetRequests()
    {
        return HttpRequest.newBuilder()
                .uri(uriComponentsBuilder.build().toUri())
                .GET();
    }
}
