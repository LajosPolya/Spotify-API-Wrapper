package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import com.lajospolya.spotifyapiwrapper.response.ClientCredentialsFlowResponse;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.http.HttpRequest;

public class ClientCredentialsFlow extends AbstractSpotifyRequest<ClientCredentialsFlowResponse>
{
    private static final String REQUEST_URI_STRING = "https://accounts.spotify.com/api/token";

    private ClientCredentialsFlow(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder
    {
        public Builder() { }

        public ClientCredentialsFlow build()
        {
            UriComponentsBuilder requestUriBuilder =  UriComponentsBuilder.fromUriString(REQUEST_URI_STRING);

            HttpRequest.Builder requestBuilder =  HttpRequest.newBuilder()
                    .uri(requestUriBuilder.build().toUri())
                .header(CONTENT_TYPE_HEADER, URL_ENCODED_CONTENT_TYPE_HEADER_VALUE)
                .POST(HttpRequest.BodyPublishers.ofByteArray(CLIENT_CREDENTIALS_GRANT_TYPE_BODY_PARAMS));
            return new ClientCredentialsFlow(requestBuilder);
        }
    }
}
