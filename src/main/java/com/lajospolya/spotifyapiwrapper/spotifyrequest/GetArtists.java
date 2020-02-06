package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import com.lajospolya.spotifyapiwrapper.client.response.Artists;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.http.HttpRequest;
import java.util.List;

public class GetArtists extends SpotifyRequest<Artists>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "artists";
    private static final UriComponentsBuilder REQUEST_URI =  UriComponentsBuilder.fromUriString(REQUEST_URI_STRING);

    private HttpRequest request;
    private HttpRequest.Builder requestBuilder;
    private String accessToken;

    private GetArtists(HttpRequest.Builder requestBuilder)
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
        private List<String> artistIds;

        public Builder(List<String> artistIds)
        {
            this.artistIds = artistIds;
        }

        public GetArtists build()
        {
            // Requires param validation
            String commaSeparatedIds = String.join(",", artistIds);
            UriComponentsBuilder artistsBuilder =  UriComponentsBuilder.fromUriString(REQUEST_URI_STRING);
            artistsBuilder.queryParam(IDS_QUERY_PARAM, commaSeparatedIds);
            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(artistsBuilder.build().toUri())
                    .GET();
            return new GetArtists(requestBuilder);
        }
    }
}
