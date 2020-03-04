package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import com.google.gson.Gson;
import com.lajospolya.spotifyapiwrapper.enumeration.FollowType;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.http.HttpRequest;
import java.util.List;

public class DeleteFollow extends AbstractSpotifyRequest<Void>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "me/following";
    // Maybe this should be a class under a bodies package
    private static final String JSON_BODY = "{\"ids\":%s}";

    private DeleteFollow(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder
    {
        // Consider this to be STATIC
        private Gson gson = new Gson();

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
            if(this.ids != null)
            {
                // Create a package for bodies
                // this and put follow has bodies objects
                String stringIds = this.gson.toJson(this.ids);
                return HttpRequest.BodyPublishers.ofString(JSON_BODY.replace("%s", stringIds));
            }
            return HttpRequest.BodyPublishers.noBody();
        }
    }
}
