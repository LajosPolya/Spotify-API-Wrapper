package com.lajospolya.spotifyapiwrapper.request;

import com.lajospolya.spotifyapiwrapper.response.AudioFeatures;

import java.net.http.HttpRequest;

public class GetAudioFeatures extends AbstractSpotifyRequest<AudioFeatures>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "audio-features/{id}";

    private GetAudioFeatures(HttpRequest.Builder requestBuilder)
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
            SpotifyRequestBuilder spotifyRequestBuilder = new SpotifyRequestBuilder(REQUEST_URI_STRING, trackId);

            return new GetAudioFeatures(spotifyRequestBuilder.createGetRequests());
        }
    }
}
