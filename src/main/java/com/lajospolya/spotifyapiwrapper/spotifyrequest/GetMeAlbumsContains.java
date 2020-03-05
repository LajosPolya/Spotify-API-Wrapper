package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import org.springframework.web.util.UriComponentsBuilder;

import java.net.http.HttpRequest;
import java.util.List;

public class GetMeAlbumsContains extends AbstractSpotifyRequest<List<Boolean>>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "me/albums/contains";

    private GetMeAlbumsContains(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder
    {
        private List<String> ids;

        public Builder(List<String> ids)
        {
            validateParametersNotNull(ids);
            spotifyRequestParamValidationService.validateFollowIds(ids);
            this.ids = ids;
        }

        public GetMeAlbumsContains build()
        {
            UriComponentsBuilder requestUriBuilder =  UriComponentsBuilder.fromUriString(REQUEST_URI_STRING);

            String commaSeparatedIds = String.join(",", this.ids);

            requestUriBuilder.queryParam(IDS_QUERY_PARAM, commaSeparatedIds);

            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(requestUriBuilder.build().toUri())
                    .GET();
            return new GetMeAlbumsContains(requestBuilder);
        }
    }
}
