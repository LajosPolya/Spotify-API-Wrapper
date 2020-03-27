package com.lajospolya.spotifyapiwrapper.request;

import com.lajospolya.spotifyapiwrapper.enumeration.TimeRange;
import com.lajospolya.spotifyapiwrapper.enumeration.UsersTopType;

import java.net.http.HttpRequest;

abstract class GetUsersTop<T> extends AbstractSpotifyRequest<T>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "me/top/{type}";

    GetUsersTop(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    abstract static class Builder<T> extends AbstractBuilder<T>
    {
        private Integer limit;
        private Integer offset;
        private TimeRange timeRange;

        Builder() { }

        HttpRequest.Builder build(UsersTopType type)
        {
            SpotifyRequestBuilder spotifyRequestBuilder = new SpotifyRequestBuilder(REQUEST_URI_STRING, type.getName());
            addOptionalQueryParams(spotifyRequestBuilder);

            return spotifyRequestBuilder.createGetRequests();
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

        void limit(Integer limit)
        {
            spotifyRequestParamValidationService.validateLimit50(limit);
            this.limit = limit;
        }

        void offset(Integer offset)
        {
            spotifyRequestParamValidationService.validateOffset(offset);
            this.offset = offset;
        }

        void timeRange(TimeRange timeRange)
        {
            this.timeRange = timeRange;
        }
    }
}
