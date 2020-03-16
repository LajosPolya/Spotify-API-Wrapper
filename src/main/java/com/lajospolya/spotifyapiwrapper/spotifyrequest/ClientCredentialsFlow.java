package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import com.lajospolya.spotifyapiwrapper.response.ClientCredentialsFlowResponse;

import java.net.http.HttpRequest;

public final class ClientCredentialsFlow extends AbstractSpotifyRequest<ClientCredentialsFlowResponse>
{
    private static final String REQUEST_URI_STRING = "https://accounts.spotify.com/api/token";

    private ClientCredentialsFlow(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder<ClientCredentialsFlow>
    {
        public Builder() { }

        @Override
        public ClientCredentialsFlow build()
        {
            SpotifyRequestBuilder spotifyRequestBuilder = new SpotifyRequestBuilder(REQUEST_URI_STRING);
            spotifyRequestBuilder.header(CONTENT_TYPE_HEADER, URL_ENCODED_CONTENT_TYPE_HEADER_VALUE);

            return new ClientCredentialsFlow(
                    spotifyRequestBuilder.createPostRequestWithStringBody(CLIENT_CREDENTIALS_GRANT_TYPE_BODY_PARAMS));
        }
    }
}
