package com.lajospolya.spotifyapiwrapper.response;

import java.util.List;

public class PagingCursor<T>
{
    private String href;
    private List<T> items;
    private Integer limit;
    private String next;
    private Cursor cursors;
    private Integer total;

    public String getHref()
    {
        return href;
    }

    public void setHref(String href)
    {
        this.href = href;
    }

    public List<T> getItems()
    {
        return items;
    }

    public void setItems(List<T> items)
    {
        this.items = items;
    }

    public Integer getLimit()
    {
        return limit;
    }

    public void setLimit(Integer limit)
    {
        this.limit = limit;
    }

    public String getNext()
    {
        return next;
    }

    public void setNext(String next)
    {
        this.next = next;
    }

    public Cursor getCursors()
    {
        return cursors;
    }

    public void setCursors(Cursor cursors)
    {
        this.cursors = cursors;
    }

    public Integer getTotal()
    {
        return total;
    }

    public void setTotal(Integer total)
    {
        this.total = total;
    }
}
