package com.lajospolya.spotifyapiwrapper.request;

import com.lajospolya.spotifyapiwrapper.component.ISpotifyRequestBuilder;
import com.lajospolya.spotifyapiwrapper.component.SpotifyClientComponentsFactory;

import java.util.List;

/**
 * @author Lajos Polya
 *
 * Represents the endpoint at GET https://api.spotify.com/v1/me/tracks/contains as descrbibed at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
public class GetMeTracksContains extends AbstractSpotifyRequest<List<Boolean>>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "me/tracks/contains";

    private GetMeTracksContains(ISpotifyRequestBuilder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder<GetMeTracksContains>
    {
        private final List<String> trackIds;

        public Builder(List<String> trackIds) throws IllegalArgumentException
        {
            spotifyRequestParamValidationService.validateParametersNotNull(trackIds);
            spotifyRequestParamValidationService.validateIds50(trackIds);
            this.trackIds = trackIds;
        }

        @Override
        public GetMeTracksContains build()
        {
            ISpotifyRequestBuilder spotifyRequestBuilder = SpotifyClientComponentsFactory.spotifyRequestBuilder(REQUEST_URI_STRING)
            .queryParam(IDS_QUERY_PARAM,  trackIds);

            return new GetMeTracksContains(spotifyRequestBuilder.GET());
        }
    }
}
