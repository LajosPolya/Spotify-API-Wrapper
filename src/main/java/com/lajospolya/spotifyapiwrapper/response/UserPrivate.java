package com.lajospolya.spotifyapiwrapper.response;

public class UserPrivate extends UserPublic
{
    private String country;
    private String email;
    private String product;

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getProduct()
    {
        return product;
    }

    public void setProduct(String product)
    {
        this.product = product;
    }
}
