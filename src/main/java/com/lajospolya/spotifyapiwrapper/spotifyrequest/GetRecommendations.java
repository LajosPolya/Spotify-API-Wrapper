package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import com.lajospolya.spotifyapiwrapper.enumeration.TuneableTrackAttributeFactory;
import com.lajospolya.spotifyapiwrapper.response.Recommendation;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.http.HttpRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetRecommendations extends AbstractSpotifyRequest<Recommendation>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "recommendations";

    private GetRecommendations(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder
    {
        private List<String> seed_artists;
        private List<String> seed_tracks;
        private List<String> seed_genres;

        private Integer limit;
        private String market;

        private Map<String, ? super Object> tuneableAttributes = new HashMap<>();


        public Builder(List<String> seed_artists, List<String> seed_tracks, List<String> seed_genres) throws IllegalArgumentException
        {
            validateParametersNotNull(seed_artists, seed_tracks, seed_genres);
            this.seed_artists = seed_artists;
            this.seed_tracks = seed_tracks;
            this.seed_genres = seed_genres;
        }

        public GetRecommendations build()
        {
            UriComponentsBuilder requestUriBuilder =  UriComponentsBuilder.fromUriString(REQUEST_URI_STRING);
            String commaSeparatedArtists = String.join(",", this.seed_artists);
            String commaSeparatedTracks = String.join(",", this.seed_tracks);
            String commaSeparatedGenres = String.join(",", this.seed_genres);
            requestUriBuilder.queryParam(SEED_ARTISTS_QUERY_PARAM, commaSeparatedArtists);
            requestUriBuilder.queryParam(SEED_TRACKS_QUERY_PARAM, commaSeparatedTracks);
            requestUriBuilder.queryParam(SEED_GENRES_QUERY_PARAM, commaSeparatedGenres);

            addOptionalQueryParams(requestUriBuilder);

            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(requestUriBuilder.build().toUri())
                    .GET();
            return new GetRecommendations(requestBuilder);
        }

        private void addOptionalQueryParams(UriComponentsBuilder requestUriBuilder)
        {
            if(this.market != null)
            {
                requestUriBuilder.queryParam(MARKET_QUERY_PARAM, this.market);
            }
            requestUriBuilder.queryParam(LIMIT_QUERY_PARAM, this.limit);

            for(Map.Entry<String, Object> pair : tuneableAttributes.entrySet())
            {
                requestUriBuilder.queryParam(pair.getKey(), pair.getValue());
            }
        }

        public Builder limit(Integer limit)
        {
            spotifyRequestParamValidationService.validateLimit100(limit);
            this.limit = limit;
            return this;
        }

        public Builder market(String market)
        {
            this.market = market;
            return this;
        }

        public <T> Builder min(TuneableTrackAttributeFactory.AbstractTuneableTrackAttribute<T> tuneableAttribute, T value)
        {
            tuneableAttribute.validate(value);
            tuneableAttributes.put(MIN_PARAM_PREFIX + tuneableAttribute.name(), value);
            return this;
        }

        public <T> Builder max(TuneableTrackAttributeFactory.AbstractTuneableTrackAttribute<T> tuneableAttribute, T value)
        {
            tuneableAttribute.validate(value);
            tuneableAttributes.put(MAX_PARAM_PREFIX + tuneableAttribute.name(), value);
            return this;
        }

        public <T> Builder target(TuneableTrackAttributeFactory.AbstractTuneableTrackAttribute<T> tuneableAttribute, T value)
        {
            tuneableAttribute.validate(value);
            tuneableAttributes.put(TARGET_PARAM_PREFIX + tuneableAttribute.name(), value);
            return this;
        }
    }
}
