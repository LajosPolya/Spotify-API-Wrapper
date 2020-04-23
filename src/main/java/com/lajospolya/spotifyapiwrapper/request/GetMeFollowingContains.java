package com.lajospolya.spotifyapiwrapper.request;

import com.lajospolya.spotifyapiwrapper.enumeration.FollowType;
import com.lajospolya.spotifyapiwrapper.component.ISpotifyRequestBuilder;
import com.lajospolya.spotifyapiwrapper.component.SpotifyClientComponentsFactory;

import java.util.List;

/**
 * @author Lajos Polya
 *
 * Represents the endpoint at GET https://api.spotify.com/v1/me/following/contains as descrbibed at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
public class GetMeFollowingContains extends AbstractSpotifyRequest<List<Boolean>>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "me/following/contains";

    private GetMeFollowingContains(ISpotifyRequestBuilder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder<GetMeFollowingContains>
    {
        private final FollowType type;
        private final List<String> ids;

        public Builder(FollowType type, List<String> ids) throws IllegalArgumentException
        {
            spotifyRequestParamValidationService.validateParametersNotNull(type, ids);
            spotifyRequestParamValidationService.validateIds50(ids);
            this.type = type;
            this.ids = ids;
        }

        @Override
        public GetMeFollowingContains build()
        {
            ISpotifyRequestBuilder spotifyRequestBuilder = SpotifyClientComponentsFactory.spotifyRequestBuilder(REQUEST_URI_STRING)
            .queryParam(IDS_QUERY_PARAM, ids)
            .queryParam(TYPE_QUERY_PARAM, type.getName());

            return new GetMeFollowingContains(spotifyRequestBuilder.GET());
        }
    }
}
