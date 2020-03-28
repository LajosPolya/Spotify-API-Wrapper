package com.lajospolya.spotifyapiwrapper.request;

import com.lajospolya.spotifyapiwrapper.response.CategorysPlaylists;

import java.net.http.HttpRequest;

/**
 * @author Lajos Polya
 *
 * Represents the endpoint at GET https://api.spotify.com/v1/browse/categories/{category_id}/playlists as descrbibed at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
public class GetCategorysPlaylists extends AbstractSpotifyRequest<CategorysPlaylists>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "browse/categories/{category_id}/playlists";

    public GetCategorysPlaylists(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder<GetCategorysPlaylists>
    {
        private String categoryId;
        private String country;
        private String locale;

        public Builder(String categoryId) throws IllegalArgumentException
        {
            spotifyRequestParamValidationService.validateParametersNotNull(categoryId);
            this.categoryId = categoryId;
        }

        @Override
        public GetCategorysPlaylists build()
        {
            SpotifyRequestBuilder spotifyRequestBuilder = new SpotifyRequestBuilder(REQUEST_URI_STRING, categoryId);
            addOptionalQueryParams(spotifyRequestBuilder);

            return new GetCategorysPlaylists(spotifyRequestBuilder.createGetRequests());
        }

        private void addOptionalQueryParams(SpotifyRequestBuilder requestUriBuilder)
        {
            if(country != null)
            {
                requestUriBuilder.queryParam(COUNTRY_QUERY_PARAM, country);
            }
            if(locale != null)
            {
                requestUriBuilder.queryParam(LOCALE_QUERY_PARAM, locale);
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