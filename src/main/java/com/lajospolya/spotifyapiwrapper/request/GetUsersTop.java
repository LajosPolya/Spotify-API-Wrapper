package com.lajospolya.spotifyapiwrapper.request;

import com.lajospolya.spotifyapiwrapper.enumeration.TimeRange;
import com.lajospolya.spotifyapiwrapper.enumeration.UsersTopType;

abstract class GetUsersTop<T> extends AbstractSpotifyRequest<T>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "me/top/{type}";

    GetUsersTop(SpotifyRequestBuilder requestBuilder)
    {
        super(requestBuilder);
    }

    abstract static class Builder<T> extends AbstractBuilder<T>
    {
        private Integer limit;
        private Integer offset;
        private TimeRange timeRange;

        Builder() { }

        SpotifyRequestBuilder build(UsersTopType type)
        {
            SpotifyRequestBuilder spotifyRequestBuilder = new SpotifyRequestBuilder(REQUEST_URI_STRING, type.getName());
            addOptionalQueryParams(spotifyRequestBuilder);

            return spotifyRequestBuilder.GET();
        }

        private void addOptionalQueryParams(SpotifyRequestBuilder requestUriBuilder)
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
