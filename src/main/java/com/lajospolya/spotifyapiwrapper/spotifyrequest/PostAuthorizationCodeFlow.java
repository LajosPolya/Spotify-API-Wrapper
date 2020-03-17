package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import com.lajospolya.spotifyapiwrapper.body.AuthorizationCode;
import com.lajospolya.spotifyapiwrapper.response.AuthorizingToken;

import java.net.http.HttpRequest;

public final class PostAuthorizationCodeFlow extends AbstractSpotifyRequest<AuthorizingToken>
{
    private static final String REQUEST_URI_STRING = "https://accounts.spotify.com/api/token";

    private PostAuthorizationCodeFlow(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder<PostAuthorizationCodeFlow>
    {
        private String code;
        private String redirectUri;

        public Builder(String code, String redirectUri) throws IllegalArgumentException
        {
            spotifyRequestParamValidationService.validateParametersNotNull(code, redirectUri);
            this.code = code;
            this.redirectUri = redirectUri;
        }

        @Override
        public PostAuthorizationCodeFlow build()
        {
            SpotifyRequestBuilder spotifyRequestBuilder = new SpotifyRequestBuilder(REQUEST_URI_STRING);
            spotifyRequestBuilder.contentType(URL_ENCODED_CONTENT_TYPE_HEADER_VALUE);

            return new PostAuthorizationCodeFlow(
                    spotifyRequestBuilder.createPostRequestWithStringBody(
                            new AuthorizationCode(code, redirectUri).toUrlEncodedString()));
        }
    }
}
