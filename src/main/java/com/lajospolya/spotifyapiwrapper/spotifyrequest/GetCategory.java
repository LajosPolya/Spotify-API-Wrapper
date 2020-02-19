package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import com.lajospolya.spotifyapiwrapper.response.Category;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.http.HttpRequest;

public class GetCategory extends AbstractSpotifyRequest<Category>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "browse/categories/{category_id}";

    public GetCategory(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder
    {
        private String categoryId;
        private String country;
        private String locale;

        public Builder(String categoryId) throws IllegalArgumentException
        {
            validateParametersNotNull(categoryId);
            this.categoryId = categoryId;
        }

        public GetCategory build()
        {
            UriComponentsBuilder requestUriBuilder =  UriComponentsBuilder.fromUriString(REQUEST_URI_STRING);

            addOptionalQueryParams(requestUriBuilder);

            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(requestUriBuilder.buildAndExpand(categoryId).toUri())
                    .GET();
            return new GetCategory(requestBuilder);
        }

        private void addOptionalQueryParams(UriComponentsBuilder requestUriBuilder)
        {
            if(this.country != null)
            {
                requestUriBuilder.queryParam(COUNTRY_QUERY_PARAM, this.country);
            }
            if(this.locale != null)
            {
                requestUriBuilder.queryParam(LOCALE_QUERY_PARAM, this.locale);
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
    }
}
