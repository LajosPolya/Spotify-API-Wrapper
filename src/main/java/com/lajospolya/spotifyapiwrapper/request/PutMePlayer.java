package com.lajospolya.spotifyapiwrapper.request;

import com.lajospolya.spotifyapiwrapper.body.Player;

import java.net.http.HttpRequest;
import java.util.List;

/**
 * @author Lajos Polya
 *
 * Represents the endpoint at PUT https://api.spotify.com/v1/me/player as descrbibed at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
public class PutMePlayer extends AbstractSpotifyRequest<Void>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "me/player";

    private PutMePlayer(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder<PutMePlayer>
    {
        private List<String> deviceIds;
        private Boolean play;

        public Builder(List<String> deviceIds) throws IllegalArgumentException
        {
            spotifyRequestParamValidationService.validateParametersNotNull(deviceIds);
            this.deviceIds = deviceIds;
        }

        @Override
        public PutMePlayer build()
        {
            SpotifyRequestBuilder spotifyRequestBuilder = new SpotifyRequestBuilder(REQUEST_URI_STRING);

            HttpRequest.Builder requestBuilder = spotifyRequestBuilder.
                    PUTWithJsonBody(new Player(deviceIds, play));
            return new PutMePlayer(requestBuilder);
        }

        public Builder play(Boolean play)
        {
            this.play = play;
            return this;
        }
    }
}
