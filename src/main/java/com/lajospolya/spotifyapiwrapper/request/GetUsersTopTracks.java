package com.lajospolya.spotifyapiwrapper.request;

import com.lajospolya.spotifyapiwrapper.enumeration.TimeRange;
import com.lajospolya.spotifyapiwrapper.enumeration.UsersTopType;
import com.lajospolya.spotifyapiwrapper.response.Paging;
import com.lajospolya.spotifyapiwrapper.response.Track;

import java.net.http.HttpRequest;

public final class GetUsersTopTracks extends GetUsersTop<Paging<Track>>
{

    private GetUsersTopTracks(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends GetUsersTop.Builder<GetUsersTopTracks>
    {
        public Builder()
        {
            super();
        }

        @Override
        public GetUsersTopTracks build()
        {
            return new GetUsersTopTracks(super.build(UsersTopType.tracks));
        }

        public Builder limit(Integer limit)
        {
            super.limit(limit);
            return this;
        }

        public Builder offset(Integer offset)
        {
            super.offset(offset);
            return this;
        }

        public Builder timeRange(TimeRange timeRange)
        {
            super.timeRange(timeRange);
            return this;
        }
    }
}
