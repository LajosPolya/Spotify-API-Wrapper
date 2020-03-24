package com.lajospolya.spotifyapiwrapper.request;

import com.lajospolya.spotifyapiwrapper.response.Paging;
import com.lajospolya.spotifyapiwrapper.response.SavedTrack;

import java.net.http.HttpRequest;

public class GetMeTracks extends AbstractSpotifyRequest<Paging<SavedTrack>>
{
    private static final String REQUEST_URI_STRING = SPOTIFY_V1_API_URI +  "me/tracks";

    private GetMeTracks(HttpRequest.Builder requestBuilder)
    {
        super(requestBuilder);
    }

    public static class Builder extends AbstractBuilder<GetMeTracks>
    {
        private Integer limit;
        private Integer offset;
        private String market;

        public Builder() { }

        @Override
        public GetMeTracks build()
        {
            SpotifyRequestBuilder spotifyRequestBuilder = new SpotifyRequestBuilder(REQUEST_URI_STRING);
            addOptionalQueryParams(spotifyRequestBuilder);

            return new GetMeTracks(spotifyRequestBuilder.createGetRequests());
        }

        private void addOptionalQueryParams(SpotifyRequestBuilder requestUriBuilder)
        {
            if(market != null)
            {
                requestUriBuilder.queryParam(MARKET_QUERY_PARAM, market);
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

        public Builder market(String market)
        {
            this.market = market;
            return this;
        }
    }
}
