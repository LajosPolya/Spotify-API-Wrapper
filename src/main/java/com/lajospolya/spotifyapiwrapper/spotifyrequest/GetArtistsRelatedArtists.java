package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import com.lajospolya.spotifyapiwrapper.client.response.Artists;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.http.HttpRequest;

public class GetArtistsRelatedArtists extends SpotifyRequest<Artists>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "artists/{id}/related-artists";
    private static final UriComponentsBuilder REQUEST_URI =  UriComponentsBuilder.fromUriString(REQUEST_URI_STRING);

    private HttpRequest request;
    private HttpRequest.Builder requestBuilder;
    private String accessToken;

    private GetArtistsRelatedArtists(HttpRequest.Builder requestBuilder)
    {
        this.requestBuilder = requestBuilder;
    }

    private HttpRequest buildRequest()
    {
        return requestBuilder
                .header(AUTHORIZATION_HEADER, this.accessToken)
                .build();
    }

    public static class Builder
    {
        private String artistId;

        public Builder(String artistId)
        {
            this.artistId = artistId;
        }

        public GetArtistsRelatedArtists build()
        {
            // Requires param validation
            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(REQUEST_URI.buildAndExpand(this.artistId).toUri())
                    .GET();
            return new GetArtistsRelatedArtists(requestBuilder);
        }
    }
}
