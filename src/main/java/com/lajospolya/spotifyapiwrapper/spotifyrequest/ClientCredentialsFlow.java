package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import com.lajospolya.spotifyapiwrapper.response.AuthorizationResponse;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.http.HttpRequest;
import java.nio.charset.StandardCharsets;

public class ClientCredentialsFlow extends AbstractSpotifyRequest<AuthorizationResponse>
{
    private static final String REQUEST_URI_STRING = "https://accounts.spotify.com/api/token";
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String URL_ENCODED_CONTENT_TYPE = "application/x-www-form-urlencoded";
    private static final byte[] GRANT_TYPE_BODY_PARAMS = "grant_type=client_credentials".getBytes(StandardCharsets.UTF_8);

    private HttpRequest request;
    private HttpRequest.Builder requestBuilder;
    private String accessToken;

    private ClientCredentialsFlow(HttpRequest.Builder requestBuilder)
    {
        this.requestBuilder = requestBuilder;
    }

    private HttpRequest buildRequest()
    {
        return requestBuilder
                .build();
    }

    private void setAccessToken(String accessToken)
    {
        this.accessToken = accessToken;
        requestBuilder.setHeader(AUTHORIZATION_HEADER, this.accessToken);
    }

    public static class Builder extends AbstractBuilder
    {
        public Builder() throws IllegalArgumentException
        {
        }

        public ClientCredentialsFlow build()
        {
            // Required param validation
            UriComponentsBuilder requestUriBuilder =  UriComponentsBuilder.fromUriString(REQUEST_URI_STRING);

            HttpRequest.Builder requestBuilder =  HttpRequest.newBuilder()
                    .uri(requestUriBuilder.build().toUri())
                .header(CONTENT_TYPE, URL_ENCODED_CONTENT_TYPE)
                .POST(HttpRequest.BodyPublishers.ofByteArray(GRANT_TYPE_BODY_PARAMS));
            return new ClientCredentialsFlow(requestBuilder);
        }
    }
}
