package com.lajospolya.spotifyapiwrapper.response;

/**
 * @author Lajos Polya
 * Represent the response of GetAllCategories as described at
 * https://developer.spotify.com/documentation/web-api/reference-beta/
 */
public class Categories
{
    Paging<Category> categories;

    public Paging<Category> getCategories()
    {
        return categories;
    }

    public void setCategories(Paging<Category> categories)
    {
        this.categories = categories;
    }
}
