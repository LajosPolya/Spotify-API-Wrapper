package com.lajospolya.spotifyapiwrapper.request;

import com.lajospolya.spotifyapiwrapper.body.RefreshToken;
import com.lajospolya.spotifyapiwrapper.response.AuthorizingToken;

import java.net.http.HttpRequest;

public final class PostRefreshToken extends AbstractSpotifyRequest<AuthorizingToken>
{
    private static final String REQUEST_URI_STRING = "https://accounts.spotify.com/api/token";

    private PostRefreshToken(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder<PostRefreshToken>
    {
        private String refreshToken;

        public Builder(String refreshToken)
        {
            spotifyRequestParamValidationService.validateParametersNotNull(refreshToken);
            this.refreshToken = refreshToken;
        }

        @Override
        public PostRefreshToken build()
        {
            SpotifyRequestBuilder spotifyRequestBuilder = new SpotifyRequestBuilder(REQUEST_URI_STRING);
            spotifyRequestBuilder.contentType(URL_ENCODED_CONTENT_TYPE_HEADER_VALUE);

            return new PostRefreshToken(
                    spotifyRequestBuilder.createPostRequestWithStringBody(
                            new RefreshToken(refreshToken).toUrlEncodedString()));
        }
    }
}
