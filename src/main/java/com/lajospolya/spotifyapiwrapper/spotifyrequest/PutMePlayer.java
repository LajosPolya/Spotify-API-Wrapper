package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import com.lajospolya.spotifyapiwrapper.body.Player;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.http.HttpRequest;
import java.util.List;

public class PutMePlayer extends AbstractSpotifyRequest<Void>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "me/player";

    private PutMePlayer(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder
    {
        private List<String> deviceIds;
        private Boolean play;

        public Builder(List<String> deviceIds) throws IllegalArgumentException
        {
            validateParametersNotNull(deviceIds);
            this.deviceIds = deviceIds;
        }

        public PutMePlayer build()
        {
            UriComponentsBuilder requestUriBuilder =  UriComponentsBuilder.fromUriString(REQUEST_URI_STRING);

            HttpRequest.BodyPublisher bodyPublisher = getBodyPublisher();

            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(requestUriBuilder.build().toUri())
                    .PUT(bodyPublisher);
            return new PutMePlayer(requestBuilder);
        }

        private HttpRequest.BodyPublisher getBodyPublisher()
        {
            return HttpRequest.BodyPublishers.ofString(gson.toJson(new Player(deviceIds, play)));
        }

        public Builder play(Boolean play)
        {
            this.play = play;
            return this;
        }
    }
}
