package com.lajospolya.spotifyapiwrapper.request;

import com.lajospolya.spotifyapiwrapper.enumeration.TimeRange;
import com.lajospolya.spotifyapiwrapper.enumeration.UsersTopType;
import com.lajospolya.spotifyapiwrapper.internal.ISpotifyRequestBuilder;
import com.lajospolya.spotifyapiwrapper.response.Paging;
import com.lajospolya.spotifyapiwrapper.response.Track;

/**
 * @author Lajos Polya
 *
 * Represents the endpoint at GET https://api.spotify.com/v1/me/top/{type} as descrbibed at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
public class GetUsersTopTracks extends GetUsersTop<Paging<Track>>
{

    private GetUsersTopTracks(ISpotifyRequestBuilder requestBuilder)
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
