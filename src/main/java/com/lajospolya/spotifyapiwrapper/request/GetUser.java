package com.lajospolya.spotifyapiwrapper.request;

import com.lajospolya.spotifyapiwrapper.response.UserPublic;

/**
 * @author Lajos Polya
 *
 * Represents the endpoint at GET https://api.spotify.com/v1/users/{id} as descrbibed at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
public class GetUser extends AbstractSpotifyRequest<UserPublic>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "users/{id}";

    private GetUser(ISpotifyRequestBuilder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder<GetUser>
    {
        private String userId;

        public Builder(String userId) throws IllegalArgumentException
        {
            spotifyRequestParamValidationService.validateParametersNotNull(userId);
            this.userId = userId;
        }

        @Override
        public GetUser build()
        {
            ISpotifyRequestBuilder spotifyRequestBuilder = SpotifyClientComponentsFactory.spotifyRequestBuilder(REQUEST_URI_STRING, userId);

            return new GetUser(spotifyRequestBuilder.GET());
        }
    }
}
