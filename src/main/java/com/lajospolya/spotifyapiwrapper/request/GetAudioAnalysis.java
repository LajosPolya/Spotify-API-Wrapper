package com.lajospolya.spotifyapiwrapper.request;

import com.lajospolya.spotifyapiwrapper.component.ISpotifyRequestBuilder;
import com.lajospolya.spotifyapiwrapper.component.SpotifyClientComponentsFactory;
import com.lajospolya.spotifyapiwrapper.response.AudioAnalysis;

/**
 * @author Lajos Polya
 *
 * Represents the endpoint at GET https://api.spotify.com/v1/audio-analysis/{id} as descrbibed at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
public class GetAudioAnalysis extends AbstractSpotifyRequest<AudioAnalysis>
{
    private static final String PATH_PARAM = "{id}";
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "audio-analysis/{id}";

    private GetAudioAnalysis(ISpotifyRequestBuilder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder<GetAudioAnalysis>
    {
        private final String trackId;

        public Builder(String trackId) throws IllegalArgumentException
        {
            spotifyRequestParamValidationService.validateParametersNotNull(trackId);
            this.trackId = trackId;
        }

        @Override
        public GetAudioAnalysis build()
        {
            ISpotifyRequestBuilder spotifyRequestBuilder = SpotifyClientComponentsFactory.spotifyRequestBuilder(REQUEST_URI_STRING)
            .pathParam(PATH_PARAM, trackId);

            return new GetAudioAnalysis(spotifyRequestBuilder.GET());
        }
    }
}
