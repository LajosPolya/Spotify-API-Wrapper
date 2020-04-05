package com.lajospolya.spotifyapiwrapper.request;

import com.lajospolya.spotifyapiwrapper.response.Category;

/**
 * @author Lajos Polya
 *
 * Represents the endpoint at GET https://api.spotify.com/v1/browse/categories/{category_id} as descrbibed at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
public class GetCategory extends AbstractSpotifyRequest<Category>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "browse/categories/{category_id}";

    public GetCategory(SpotifyRequestBuilder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder<GetCategory>
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
        public GetCategory build()
        {
            SpotifyRequestBuilder spotifyRequestBuilder = new SpotifyRequestBuilder(REQUEST_URI_STRING, categoryId);
            addOptionalQueryParams(spotifyRequestBuilder);

            return new GetCategory(spotifyRequestBuilder.GET());
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
