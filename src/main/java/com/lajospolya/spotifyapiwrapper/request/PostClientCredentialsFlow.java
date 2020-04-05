package com.lajospolya.spotifyapiwrapper.request;

import com.lajospolya.spotifyapiwrapper.response.AuthorizingToken;

/**
 * @author Lajos Polya
 *
 * Represents the endpoint at POST https://accounts.spotify.com/api/token as descrbibed at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
public class PostClientCredentialsFlow extends AbstractSpotifyRequest<AuthorizingToken>
{
    private static final String REQUEST_URI_STRING = "https://accounts.spotify.com/api/token";

    private PostClientCredentialsFlow(SpotifyRequestBuilder requestBuilder)
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
                    spotifyRequestBuilder.POSTWithStringBody(CLIENT_CREDENTIALS_GRANT_TYPE_BODY_PARAMS));
        }
    }
}
