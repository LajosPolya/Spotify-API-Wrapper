package com.lajospolya.spotifyapiwrapper.request;

import com.lajospolya.spotifyapiwrapper.enumeration.TimeRange;
import com.lajospolya.spotifyapiwrapper.enumeration.UsersTopType;
import com.lajospolya.spotifyapiwrapper.component.ISpotifyRequestBuilder;
import com.lajospolya.spotifyapiwrapper.component.SpotifyClientComponentsFactory;

abstract class GetUsersTop<T> extends AbstractSpotifyRequest<T>
{
    private static final String PATH_PARAM = "{type}";
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "me/top/{type}";

    GetUsersTop(ISpotifyRequestBuilder requestBuilder)
    {
        super(requestBuilder);
    }

    abstract static class Builder<T> extends AbstractBuilder<T>
    {
        private Integer limit;
        private Integer offset;
        private TimeRange timeRange;

        Builder() { }

        ISpotifyRequestBuilder build(UsersTopType type)
        {
            ISpotifyRequestBuilder spotifyRequestBuilder = SpotifyClientComponentsFactory.spotifyRequestBuilder(REQUEST_URI_STRING)
            .pathParam(PATH_PARAM, type.getName());
            addOptionalQueryParams(spotifyRequestBuilder);

            return spotifyRequestBuilder.GET();
        }

        private void addOptionalQueryParams(ISpotifyRequestBuilder requestUriBuilder)
        {
            if(limit != null)
            {
                requestUriBuilder.queryParam(LIMIT_QUERY_PARAM, limit);
            }
            if(offset != null)
            {
                requestUriBuilder.queryParam(OFFSET_QUERY_PARAM, offset);
            }
            if(timeRange != null)
            {
                requestUriBuilder.queryParam(TIME_RANGE_QUERY_PARAM, timeRange.getName());
            }
        }

        Builder<T> limit(Integer limit)
        {
            spotifyRequestParamValidationService.validateLimit50(limit);
            this.limit = limit;
            return this;
        }

        Builder<T> offset(Integer offset)
        {
            spotifyRequestParamValidationService.validateOffset(offset);
            this.offset = offset;
            return this;
        }

        Builder<T> timeRange(TimeRange timeRange)
        {
            this.timeRange = timeRange;
            return this;
        }
    }
}
