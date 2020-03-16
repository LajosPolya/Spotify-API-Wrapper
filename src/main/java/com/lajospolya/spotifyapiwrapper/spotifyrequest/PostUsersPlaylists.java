package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import com.lajospolya.spotifyapiwrapper.body.PlaylistDetails;
import com.lajospolya.spotifyapiwrapper.response.Playlist;

import java.net.http.HttpRequest;

public final class PostUsersPlaylists extends AbstractSpotifyRequest<Playlist>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "users/{user_id}/playlists";

    private PostUsersPlaylists(HttpRequest.Builder requestBuilder)
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
            SpotifyRequestBuilder spotifyRequestBuilder = new SpotifyRequestBuilder(REQUEST_URI_STRING, userId);
            spotifyRequestBuilder.header(CONTENT_TYPE_HEADER, APPLICATION_JSON_CONTENT_TYPE_HEADER_VALUE);

            return new PostUsersPlaylists(
                    spotifyRequestBuilder.createPostRequestWithObjectJsonBody(
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
