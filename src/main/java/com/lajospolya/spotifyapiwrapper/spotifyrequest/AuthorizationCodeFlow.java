package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import com.lajospolya.spotifyapiwrapper.response.AuthorizationCodeFlowResponse;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.http.HttpRequest;

public class AuthorizationCodeFlow extends AbstractSpotifyRequest<AuthorizationCodeFlowResponse>
{
    private static final String REQUEST_URI_STRING = "https://accounts.spotify.com/api/token";

    private AuthorizationCodeFlow(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder
    {
        private String code;
        private String redirectUri;

        public Builder(String code, String redirectUri) throws IllegalArgumentException
        {
            validateParametersNotNull(code, redirectUri);
            this.code = code;
            this.redirectUri = redirectUri;
        }

        public AuthorizationCodeFlow build()
        {
            UriComponentsBuilder requestUriBuilder =  UriComponentsBuilder.fromUriString(REQUEST_URI_STRING);

            requestUriBuilder.queryParam(CODE_QUERY_PARAM, code);
            requestUriBuilder.queryParam(REDIRECT_URI_QUERY_PARAM, redirectUri);

            HttpRequest.Builder requestBuilder =  HttpRequest.newBuilder()
                    .uri(requestUriBuilder.build().toUri())
                    .header(CONTENT_TYPE_HEADER, URL_ENCODED_CONTENT_TYPE_HEADER_VALUE)
                    .POST(HttpRequest.BodyPublishers.ofString(AUTHORIZATION_CODE_GRANT_TYPE_BODY_PARAMS +"&code=" + code + "&redirect_uri=" + redirectUri));
            return new AuthorizationCodeFlow(requestBuilder);
        }
    }
}
