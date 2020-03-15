package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import com.lajospolya.spotifyapiwrapper.response.CategorysPlaylists;

import java.net.http.HttpRequest;

public class GetCategorysPlaylists extends AbstractSpotifyRequest<CategorysPlaylists>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "browse/categories/{category_id}/playlists";

    public GetCategorysPlaylists(HttpRequest.Builder requestBuilder)
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

        public GetCategorysPlaylists build()
        {
            SpotifyRequestBuilder spotifyRequestBuilder = new SpotifyRequestBuilder(REQUEST_URI_STRING, categoryId);
            addOptionalQueryParams(spotifyRequestBuilder);

            return new GetCategorysPlaylists(spotifyRequestBuilder.createGetRequests());
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
