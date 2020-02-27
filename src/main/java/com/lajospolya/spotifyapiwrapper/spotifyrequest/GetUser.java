package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import com.lajospolya.spotifyapiwrapper.response.UserPublic;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.http.HttpRequest;

public class GetUser extends AbstractSpotifyRequest<UserPublic>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "users/{id}";

    private GetUser(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder
    {
        private String userId;

        public Builder(String userId) throws IllegalArgumentException
        {
            validateParametersNotNull(userId);
            this.userId = userId;
        }

        public GetUser build()
        {
            UriComponentsBuilder requestUriBuilder =  UriComponentsBuilder.fromUriString(REQUEST_URI_STRING);

            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(requestUriBuilder.buildAndExpand(this.userId).toUri())
                    .GET();
            return new GetUser(requestBuilder);
        }
    }
}
