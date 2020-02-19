package com.lajospolya.spotifyapiwrapper.response;

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
