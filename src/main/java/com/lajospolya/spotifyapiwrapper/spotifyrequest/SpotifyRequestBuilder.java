package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import com.google.gson.Gson;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.http.HttpRequest;

public class SpotifyRequestBuilder
{
    private static final Gson gson = new Gson();
    private String uri;
    private UriComponentsBuilder uriComponentsBuilder;

    SpotifyRequestBuilder(String uri)
    {
        this.uri = uri;
        this.uriComponentsBuilder = UriComponentsBuilder.fromUriString(uri);
    }

    void addQueryParam(String name, Object value)
    {
        uriComponentsBuilder.queryParam(name, value);
    }

    HttpRequest.Builder createGetRequests()
    {
        return createBuilderWithUri()
                .GET();
    }

    HttpRequest.Builder createPostRequests()
    {
        return createBuilderWithUri()
                .POST(HttpRequest.BodyPublishers.noBody());
    }

    HttpRequest.Builder createPutRequest()
    {
        return createBuilderWithUri()
                .PUT(HttpRequest.BodyPublishers.noBody());
    }

    HttpRequest.Builder createPutRequestWithObjectJsonBody(Object body)
    {
        return createBuilderWithUri()
                .PUT(HttpRequest.BodyPublishers.ofString(gson.toJson(body)));
    }

    private HttpRequest.Builder createBuilderWithUri()
    {
        return HttpRequest.newBuilder()
            .uri(uriComponentsBuilder.build().toUri());
    }
}
