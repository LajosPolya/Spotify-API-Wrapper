package com.lajospolya.spotifyapiwrapper.request;

import com.lajospolya.spotifyapiwrapper.internal.SpotifyClientComponentsFactory;
import com.lajospolya.spotifyapiwrapper.response.AudioFeatures;

/**
 * @author Lajos Polya
 *
 * Represents the endpoint at GET https://api.spotify.com/v1/audio-features/{id} as descrbibed at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
public class GetAudioFeatures extends AbstractSpotifyRequest<AudioFeatures>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "audio-features/{id}";

    private GetAudioFeatures(ISpotifyRequestBuilder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder<GetAudioFeatures>
    {
        private String trackId;

        public Builder(String trackId) throws IllegalArgumentException
        {
            spotifyRequestParamValidationService.validateParametersNotNull(trackId);
            this.trackId = trackId;
        }

        @Override
        public GetAudioFeatures build()
        {
            ISpotifyRequestBuilder spotifyRequestBuilder = SpotifyClientComponentsFactory.spotifyRequestBuilder(REQUEST_URI_STRING, trackId);

            return new GetAudioFeatures(spotifyRequestBuilder.GET());
        }
    }
}
