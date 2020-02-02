package com.lajospolya.spotifyapiwrapper;

import java.io.IOException;

public interface WebService<T>
{
    T sendRequest() throws IOException, InterruptedException;
}
