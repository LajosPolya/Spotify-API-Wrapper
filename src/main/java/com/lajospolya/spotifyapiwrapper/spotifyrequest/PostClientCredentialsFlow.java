package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import com.lajospolya.spotifyapiwrapper.response.AuthorizingToken;

import java.net.http.HttpRequest;

public final class PostClientCredentialsFlow extends AbstractSpotifyRequest<AuthorizingToken>
{
    private static final String REQUEST_URI_STRING = "https://accounts.spotify.com/api/token";

    private PostClientCredentialsFlow(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder<PostClientCredentialsFlow>
    {
        public Builder() { }

        @Override
        public PostClientCredentialsFlow build()
        {
            SpotifyRequestBuilder spotifyRequestBuilder = new SpotifyRequestBuilder(REQUEST_URI_STRING);
            spotifyRequestBuilder.contentType(URL_ENCODED_CONTENT_TYPE_HEADER_VALUE);

            return new PostClientCredentialsFlow(
                    spotifyRequestBuilder.createPostRequestWithStringBody(CLIENT_CREDENTIALS_GRANT_TYPE_BODY_PARAMS));
        }
    }
}
