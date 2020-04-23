package com.lajospolya.spotifyapiwrapper.request;

import com.lajospolya.spotifyapiwrapper.body.AuthorizationCode;
import com.lajospolya.spotifyapiwrapper.component.ISpotifyRequestBuilder;
import com.lajospolya.spotifyapiwrapper.component.SpotifyClientComponentsFactory;
import com.lajospolya.spotifyapiwrapper.response.AuthorizingToken;

/**
 * @author Lajos Polya
 *
 * Represents the endpoint at POST https://accounts.spotify.com/api/token as descrbibed at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
public class PostAuthorizationCodeFlow extends AbstractSpotifyRequest<AuthorizingToken>
{
    private static final String REQUEST_URI_STRING = "https://accounts.spotify.com/api/token";

    private PostAuthorizationCodeFlow(ISpotifyRequestBuilder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder<PostAuthorizationCodeFlow>
    {
        private final String code;
        private final String redirectUri;

        public Builder(String code, String redirectUri) throws IllegalArgumentException
        {
            spotifyRequestParamValidationService.validateParametersNotNull(code, redirectUri);
            this.code = code;
            this.redirectUri = redirectUri;
        }

        @Override
        public PostAuthorizationCodeFlow build()
        {
            ISpotifyRequestBuilder spotifyRequestBuilder = SpotifyClientComponentsFactory.spotifyRequestBuilder(REQUEST_URI_STRING)
            .contentType(URL_ENCODED_CONTENT_TYPE_HEADER_VALUE);

            return new PostAuthorizationCodeFlow(
                    spotifyRequestBuilder.POSTWithStringBody(
                            new AuthorizationCode(code, redirectUri).toUrlEncodedString()));
        }
    }
}
