package com.lajospolya.spotifyapiwrapper.client;

import com.google.gson.Gson;
import com.lajospolya.spotifyapiwrapper.spotifyexception.SpotifyErrorContainer;
import com.lajospolya.spotifyapiwrapper.spotifyexception.SpotifyResponseException;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class SpotifyApiClientService implements ISpotifyApiClientService
{
    private HttpClient httpClient;
    private Gson gson;

    public SpotifyApiClientService()
    {
        this.httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();
        this.gson =  new Gson();
    }

    public <T> T sendRequestAndFetchResponse(HttpRequest request, Type typeOfReturnValue) throws IOException, InterruptedException, SpotifyResponseException
    {
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        validateResponse(response);
        String body = response.body();

        if(isStringType(typeOfReturnValue))
        {
            return (T) body;
        }
        return gson.fromJson(body, typeOfReturnValue);
    }

    private void validateResponse(HttpResponse<String> response) throws SpotifyResponseException
    {
        int statusCode = response.statusCode();
        if(isClientErrorStatusCode(statusCode) || isServerErrorStatusCode(statusCode))
        {
            SpotifyErrorContainer error = gson.fromJson(response.body(), SpotifyErrorContainer.class);
            throw new SpotifyResponseException(error);
        }
    }

    private Boolean isClientErrorStatusCode(int statusCode)
    {
        return statusCode / 100 == 4;
    }

    private Boolean isServerErrorStatusCode(int statusCode)
    {
        return statusCode / 100 == 5;
    }

    private Boolean isStringType(Type typeOfReturnValue)
    {
        return String.class.getTypeName().equals(typeOfReturnValue.getTypeName());
    }

    public String getBase64EncodedAuthorizationKey(String clientId, String clientSecret)
    {
        byte[] authorizationKey = (clientId + ":" + clientSecret).getBytes(StandardCharsets.UTF_8);
        return Base64.getEncoder().encodeToString(authorizationKey);
    }
}
