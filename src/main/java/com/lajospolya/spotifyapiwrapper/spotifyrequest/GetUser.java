package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import com.lajospolya.spotifyapiwrapper.response.UserPublic;

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
            spotifyRequestParamValidationService.validateParametersNotNull(userId);
            this.userId = userId;
        }

        public GetUser build()
        {
            SpotifyRequestBuilder spotifyRequestBuilder = new SpotifyRequestBuilder(REQUEST_URI_STRING, userId);

            return new GetUser(spotifyRequestBuilder.createGetRequests());
        }
    }
}
