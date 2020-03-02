package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import org.springframework.web.util.UriComponentsBuilder;

import java.net.http.HttpRequest;
import java.util.List;

public class GetMeFollowing extends AbstractSpotifyRequest<List<Boolean>>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "me/following/contains";

    private GetMeFollowing(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder
    {
        private String type;
        private List<String> ids;

        public Builder(String type, List<String> ids)
        {
            validateParametersNotNull(type, ids);
            this.type = type;
            this.ids = ids;
        }

        public GetMeFollowing build()
        {
            UriComponentsBuilder requestUriBuilder =  UriComponentsBuilder.fromUriString(REQUEST_URI_STRING);

            String commaSeparatedIds = String.join(",", this.ids);

            requestUriBuilder.queryParam(IDS_QUERY_PARAM, commaSeparatedIds);
            requestUriBuilder.queryParam(TYPE_QUERY_PARAM, type);

            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(requestUriBuilder.build().toUri())
                    .GET();
            return new GetMeFollowing(requestBuilder);
        }
    }
}
