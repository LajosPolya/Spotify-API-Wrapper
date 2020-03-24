package com.lajospolya.spotifyapiwrapper.request;

import com.lajospolya.spotifyapiwrapper.response.TracksAudioFeatures;

import java.net.http.HttpRequest;
import java.util.List;

public class GetSeveralAudioFeatures extends AbstractSpotifyRequest<TracksAudioFeatures>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "audio-features";

    private GetSeveralAudioFeatures(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder<GetSeveralAudioFeatures>
    {
        private List<String> trackIds;

        public Builder(List<String> trackIds) throws IllegalArgumentException
        {
            spotifyRequestParamValidationService.validateParametersNotNull(trackIds);
            this.trackIds = trackIds;
        }

        @Override
        public GetSeveralAudioFeatures build()
        {
            SpotifyRequestBuilder spotifyRequestBuilder = new SpotifyRequestBuilder(REQUEST_URI_STRING);
            spotifyRequestBuilder.queryParam(IDS_QUERY_PARAM, trackIds);

            return new GetSeveralAudioFeatures(spotifyRequestBuilder.createGetRequests());
        }
    }
}
