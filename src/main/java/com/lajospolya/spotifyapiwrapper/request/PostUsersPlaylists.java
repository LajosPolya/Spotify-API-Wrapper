package com.lajospolya.spotifyapiwrapper.request;

import com.lajospolya.spotifyapiwrapper.body.PlaylistDetails;
import com.lajospolya.spotifyapiwrapper.internal.ISpotifyRequestBuilder;
import com.lajospolya.spotifyapiwrapper.internal.SpotifyClientComponentsFactory;
import com.lajospolya.spotifyapiwrapper.response.Playlist;

/**
 * @author Lajos Polya
 *
 * Represents the endpoint at POST https://api.spotify.com/v1/users/{user_id}/playlists as descrbibed at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
public class PostUsersPlaylists extends AbstractSpotifyRequest<Playlist>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "users/{user_id}/playlists";

    private PostUsersPlaylists(ISpotifyRequestBuilder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder<PostUsersPlaylists>
    {
        private String userId;
        private String name;
        private Boolean isPublic;
        private Boolean collaborative;
        private String description;

        public Builder(String userId, String name) throws IllegalArgumentException
        {
            spotifyRequestParamValidationService.validateParametersNotNull(userId, name);
            this.userId = userId;
            this.name = name;
        }

        @Override
        public PostUsersPlaylists build()
        {
            ISpotifyRequestBuilder spotifyRequestBuilder = SpotifyClientComponentsFactory.spotifyRequestBuilder(REQUEST_URI_STRING, userId);
            spotifyRequestBuilder.contentType(APPLICATION_JSON_CONTENT_TYPE_HEADER_VALUE);

            return new PostUsersPlaylists(
                    spotifyRequestBuilder.POSTWithJsonBody(
                            new PlaylistDetails(name, isPublic, collaborative, description)));
        }

        public Builder isPublic(Boolean isPublic)
        {
            this.isPublic = isPublic;
            return this;
        }

        public Builder collaborative(Boolean collaborative)
        {
            this.collaborative = collaborative;
            return this;
        }

        public Builder description(String description)
        {
            this.description = description;
            return this;
        }
    }
}
