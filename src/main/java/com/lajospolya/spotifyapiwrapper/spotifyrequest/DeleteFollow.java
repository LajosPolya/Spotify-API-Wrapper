package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import com.lajospolya.spotifyapiwrapper.body.DeleteMeFollowing;
import com.lajospolya.spotifyapiwrapper.enumeration.FollowType;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.http.HttpRequest;
import java.util.List;

public class DeleteFollow extends AbstractSpotifyRequest<Void>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "me/following";

    private DeleteFollow(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder
    {
        private FollowType type;
        private List<String> ids;

        public Builder(FollowType type, List<String> ids)
        {
            validateParametersNotNull(type, ids);
            spotifyRequestParamValidationService.validateFollowIds(ids);
            this.type = type;
            this.ids = ids;
        }

        public DeleteFollow build()
        {
            UriComponentsBuilder requestUriBuilder =  UriComponentsBuilder.fromUriString(REQUEST_URI_STRING);

            String commaSeparatedIds = String.join(",", this.ids);
            requestUriBuilder.queryParam(IDS_QUERY_PARAM, commaSeparatedIds);
            requestUriBuilder.queryParam(TYPE_QUERY_PARAM, type.getName());

            HttpRequest.BodyPublisher bodyPublisher = getBodyPublisher();

            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(requestUriBuilder.build().toUri())
                    .header(CONTENT_TYPE_HEADER, APPLICATION_JSON_CONTENT_TYPE_HEADER_VALUE)
                    .method(DELETE, bodyPublisher);
            return new DeleteFollow(requestBuilder);
        }

        private HttpRequest.BodyPublisher getBodyPublisher()
        {
            return HttpRequest.BodyPublishers.ofString(gson.toJson(new DeleteMeFollowing(this.ids)));
        }
    }
}
