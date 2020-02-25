package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import com.lajospolya.spotifyapiwrapper.response.NewReleases;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.http.HttpRequest;

public class GetAllNewReleases extends AbstractSpotifyRequest<NewReleases>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "browse/new-releases";

    private GetAllNewReleases(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder
    {
        private String country;
        private Integer limit;
        private Integer offset;

        public Builder() throws IllegalArgumentException
        {}

        public GetAllNewReleases build()
        {
            UriComponentsBuilder requestUriBuilder =  UriComponentsBuilder.fromUriString(REQUEST_URI_STRING);

            addOptionalQueryParams(requestUriBuilder);

            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(requestUriBuilder.build().toUri())
                    .GET();
            return new GetAllNewReleases(requestBuilder);
        }

        private void addOptionalQueryParams(UriComponentsBuilder requestUriBuilder)
        {
            if(this.country != null)
            {
                requestUriBuilder.queryParam(COUNTRY_QUERY_PARAM, this.country);
            }
            if(this.limit != null)
            {
                requestUriBuilder.queryParam(LIMIT_QUERY_PARAM, this.limit);
            }
            if(this.offset != null)
            {
                requestUriBuilder.queryParam(OFFSET_QUERY_PARAM, this.offset);
            }
        }

        public Builder country(String country)
        {
            this.country = country;
            return this;
        }

        public Builder limit(Integer limit)
        {
            if(limit < 1 || limit > 50)
            {
                throw new IllegalArgumentException(ILLEGAL_LIMIT_EXCEPTION_MSG);
            }
            this.limit = limit;
            return this;
        }

        public Builder offset(Integer offset)
        {
            if(offset < 0)
            {
                throw new IllegalArgumentException(ILLEGAL_OFFSET_EXCEPTION_MSG);
            }
            this.offset = offset;
            return this;
        }
    }
}
