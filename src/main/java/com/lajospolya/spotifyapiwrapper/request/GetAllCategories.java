package com.lajospolya.spotifyapiwrapper.request;

import com.lajospolya.spotifyapiwrapper.response.Categories;

/**
 * @author Lajos Polya
 *
 * Represents the endpoint at GET https://api.spotify.com/v1/browse/categories as descrbibed at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
public class GetAllCategories extends AbstractSpotifyRequest<Categories>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "browse/categories";

    private GetAllCategories(ISpotifyRequestBuilder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder<GetAllCategories>
    {
        private String country;
        private String locale;
        private Integer limit;
        private Integer offset;

        public Builder() { }

        @Override
        public GetAllCategories build()
        {
            ISpotifyRequestBuilder spotifyRequestBuilder = SpotifyClientComponentsFactory.spotifyRequestBuilder(REQUEST_URI_STRING);
            addOptionalQueryParams(spotifyRequestBuilder);

            return new GetAllCategories(spotifyRequestBuilder.GET());
        }

        private void addOptionalQueryParams(ISpotifyRequestBuilder requestUriBuilder)
        {
            if(country != null)
            {
                requestUriBuilder.queryParam(COUNTRY_QUERY_PARAM, country);
            }
            if(locale != null)
            {
                requestUriBuilder.queryParam(LOCALE_QUERY_PARAM, locale);
            }
            if(limit != null)
            {
                requestUriBuilder.queryParam(LIMIT_QUERY_PARAM, limit);
            }
            if(offset != null)
            {
                requestUriBuilder.queryParam(OFFSET_QUERY_PARAM, offset);
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
    }
}
