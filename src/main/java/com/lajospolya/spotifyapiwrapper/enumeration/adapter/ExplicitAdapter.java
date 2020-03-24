package com.lajospolya.spotifyapiwrapper.enumeration.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.lajospolya.spotifyapiwrapper.enumeration.Explicit;

import java.io.IOException;

public class ExplicitAdapter extends TypeAdapter<Explicit>
{
    @Override
    public void write(JsonWriter out, Explicit value) throws UnsupportedOperationException
    {
        throw new UnsupportedOperationException("ExplicitAdapter has not implemented write method");
    }

    @Override
    public Explicit read(JsonReader in) throws IOException
    {
        if(in.peek() == JsonToken.NULL)
        {
            in.nextNull();
            return null;
        }
        else if(in.peek() == JsonToken.BOOLEAN)
        {
            if(in.nextBoolean())
            {
                return Explicit.TRUE;
            }
            return Explicit.FALSE;
        }
        else if(in.peek() == JsonToken.STRING)
        {
            return Explicit.getFromString(in.nextString());
        }
        return Explicit.UNKNOWN;
    }
}
