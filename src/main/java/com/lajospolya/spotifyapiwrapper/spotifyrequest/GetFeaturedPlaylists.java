package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import com.lajospolya.spotifyapiwrapper.response.FeaturedPlaylists;

import java.net.http.HttpRequest;

public class GetFeaturedPlaylists extends AbstractSpotifyRequest<FeaturedPlaylists>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "browse/featured-playlists";

    private GetFeaturedPlaylists(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder
    {
        private String country;
        private String locale;
        private Integer limit;
        private Integer offset;
        private String timestamp;

        public Builder() { }

        public GetFeaturedPlaylists build()
        {
            SpotifyRequestBuilder spotifyRequestBuilder = new SpotifyRequestBuilder(REQUEST_URI_STRING);
            addOptionalQueryParams(spotifyRequestBuilder);

            return new GetFeaturedPlaylists(spotifyRequestBuilder.createGetRequests());
        }

        private void addOptionalQueryParams(SpotifyRequestBuilder requestUriBuilder)
        {
            if(this.country != null)
            {
                requestUriBuilder.queryParam(COUNTRY_QUERY_PARAM, this.country);
            }
            if(this.locale != null)
            {
                requestUriBuilder.queryParam(LOCALE_QUERY_PARAM, this.locale);
            }
            if(this.limit != null)
            {
                requestUriBuilder.queryParam(LIMIT_QUERY_PARAM, this.limit);
            }
            if(this.offset != null)
            {
                requestUriBuilder.queryParam(OFFSET_QUERY_PARAM, this.offset);
            }
            if(this.timestamp != null)
            {
                requestUriBuilder.queryParam(TIMESTAMP_QUERY_PARAM, this.timestamp);
            }
        }

        public Builder country(String country)
        {
            this.country = country;
            return this;
        }

        public Builder locale(String locale)
        {
            this.locale = locale;
            return this;
        }

        public Builder limit(Integer limit)
        {
            spotifyRequestParamValidationService.validateLimit50(limit);
            this.limit = limit;
            return this;
        }

        public Builder offset(Integer offset)
        {
            spotifyRequestParamValidationService.validateOffset(offset);
            this.offset = offset;
            return this;
        }

        public Builder timestamp(String timestamp)
        {
            this.timestamp = timestamp;
            return this;
        }
    }
}
