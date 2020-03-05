package com.lajospolya.spotifyapiwrapper.spotifyrequest;

import com.lajospolya.spotifyapiwrapper.enumeration.TimeRange;
import com.lajospolya.spotifyapiwrapper.enumeration.UsersTopType;
import com.lajospolya.spotifyapiwrapper.response.Artist;
import com.lajospolya.spotifyapiwrapper.response.Paging;

import java.net.http.HttpRequest;

public class GetUsersTopArtists extends GetUsersTop<Paging<Artist>>
{

    private GetUsersTopArtists(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends GetUsersTop.Builder
    {
        public Builder()
        {
            super();
        }

        public GetUsersTopArtists build()
        {
            return new GetUsersTopArtists(super.build(UsersTopType.artists));
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