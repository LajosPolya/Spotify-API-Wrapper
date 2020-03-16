package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import com.lajospolya.spotifyapiwrapper.body.AuthorizationCode;
import com.lajospolya.spotifyapiwrapper.response.AuthorizationCodeFlowResponse;

import java.net.http.HttpRequest;

public class AuthorizationCodeFlow extends AbstractSpotifyRequest<AuthorizationCodeFlowResponse>
{
    private static final String REQUEST_URI_STRING = "https://accounts.spotify.com/api/token";

    private AuthorizationCodeFlow(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder<AuthorizationCodeFlow>
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
        public AuthorizationCodeFlow build()
        {
            SpotifyRequestBuilder spotifyRequestBuilder = new SpotifyRequestBuilder(REQUEST_URI_STRING);
            spotifyRequestBuilder.header(CONTENT_TYPE_HEADER, URL_ENCODED_CONTENT_TYPE_HEADER_VALUE);

            return new AuthorizationCodeFlow(
                    spotifyRequestBuilder.createPostRequestWithStringBody(
                            new AuthorizationCode(code, redirectUri).toUrlEncodedString()));
        }
    }
}
