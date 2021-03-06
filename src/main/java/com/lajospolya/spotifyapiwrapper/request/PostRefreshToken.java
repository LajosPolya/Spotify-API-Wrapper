package com.lajospolya.spotifyapiwrapper.request;

import com.lajospolya.spotifyapiwrapper.body.RefreshToken;
import com.lajospolya.spotifyapiwrapper.component.ISpotifyRequestBuilder;
import com.lajospolya.spotifyapiwrapper.component.SpotifyClientComponentsFactory;
import com.lajospolya.spotifyapiwrapper.response.AuthorizingToken;

/**
 * @author Lajos Polya
 *
 * Represents the endpoint at POST https://accounts.spotify.com/api/token as descrbibed at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
public class PostRefreshToken extends AbstractSpotifyRequest<AuthorizingToken>
{
    private static final String REQUEST_URI_STRING = "https://accounts.spotify.com/api/token";

    private PostRefreshToken(ISpotifyRequestBuilder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder<PostRefreshToken>
    {
        private final String refreshToken;

        public Builder(String refreshToken)
        {
            spotifyRequestParamValidationService.validateParametersNotNull(refreshToken);
            this.refreshToken = refreshToken;
        }

        @Override
        public PostRefreshToken build()
        {
            ISpotifyRequestBuilder spotifyRequestBuilder = SpotifyClientComponentsFactory.spotifyRequestBuilder(REQUEST_URI_STRING)
            .contentType(URL_ENCODED_CONTENT_TYPE_HEADER_VALUE);

            return new PostRefreshToken(
                    spotifyRequestBuilder.POSTWithStringBody(
                            new RefreshToken(refreshToken).toUrlEncodedString()));
        }
    }
}
