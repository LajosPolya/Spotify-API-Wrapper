package com.lajospolya.spotifyapiwrapper.request;

import com.lajospolya.spotifyapiwrapper.enumeration.TimeRange;
import com.lajospolya.spotifyapiwrapper.enumeration.UsersTopType;
import com.lajospolya.spotifyapiwrapper.internal.ISpotifyRequestBuilder;
import com.lajospolya.spotifyapiwrapper.response.Artist;
import com.lajospolya.spotifyapiwrapper.response.Paging;

/**
 * @author Lajos Polya
 *
 * Represents the endpoint at GET https://api.spotify.com/v1/me/top/{type} as descrbibed at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
public class GetUsersTopArtists extends GetUsersTop<Paging<Artist>>
{

    private GetUsersTopArtists(ISpotifyRequestBuilder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends GetUsersTop.Builder<GetUsersTopArtists>
    {
        public Builder()
        {
            super();
        }

        @Override
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
