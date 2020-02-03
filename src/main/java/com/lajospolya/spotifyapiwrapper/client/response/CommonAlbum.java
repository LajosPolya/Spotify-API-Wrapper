package com.lajospolya.spotifyapiwrapper.client.response;

import java.util.List;
import java.util.Map;

public class CommonAlbum
{
    private String album_type;
    private List<SimplifiedArtist> artists;
    private List<String> available_markets;
    private Map<String, String> external_urls;
    private String href;
    private List<Image> images;
    private String name;
    private String release_date;
    private String release_date_precision;
    private Map<String, String> restrictions;
    private String type;
    private String uri;
}
