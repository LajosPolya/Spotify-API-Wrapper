package com.lajospolya.spotifyapiwrapper.client.service;

import com.google.gson.Gson;
import com.lajospolya.spotifyapiwrapper.cachable.CachableResponse;
import com.lajospolya.spotifyapiwrapper.response.SpotifyErrorContainer;
import com.lajospolya.spotifyapiwrapper.spotifyexception.SpotifyResponseException;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.concurrent.CompletableFuture;

public class SpotifyApiClientService implements ISpotifyApiClientService
{
    private static final String ETAG_HEADER = "etag";
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

    @Override
    public <T> T sendRequestAndFetchResponse(HttpRequest request, Type typeOfReturnValue) throws IOException, InterruptedException, SpotifyResponseException
    {
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return validateResponseAndSerialize(response, typeOfReturnValue);
    }

    @Override
    public <T> CompletableFuture<T> sendRequestAndFetchResponseAsync(HttpRequest request, Type typeOfReturnValue) throws SpotifyResponseException
    {
        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply((response) -> validateResponseAndSerialize(response, typeOfReturnValue));
    }

    private <T> T validateResponseAndSerialize(HttpResponse<String> response, Type typeOfReturnValue)
    {
        validateResponse(response);
        String body = response.body();

        /*
         * when a 304 is returned with an empty body the serialized body becomes null
         * so we don't need to handled caching separately
         */
        T serializedResponse = serializeResponseBody(body, typeOfReturnValue);
        setCachableValuesFromHeadersIfCachable(serializedResponse, response.headers());

        return serializedResponse;
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

    private <T> T serializeResponseBody(String body, Type typeOfReturnValue)
    {
        if(isStringType(typeOfReturnValue))
        {
            return (T) body;
        }
        return gson.fromJson(body, typeOfReturnValue);
    }

    private <T> void setCachableValuesFromHeadersIfCachable(T response, HttpHeaders headers)
    {
        if(response instanceof CachableResponse)
        {
            headers.firstValue(ETAG_HEADER).ifPresent(((CachableResponse)response)::setEtag);
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

    @Override
    public String getBase64EncodedAuthorizationKey(String clientId, String clientSecret)
    {
        byte[] authorizationKey = (clientId + ":" + clientSecret).getBytes(StandardCharsets.UTF_8);
        return Base64.getEncoder().encodeToString(authorizationKey);
    }

    @Override
    public Boolean hasTokenExpired(Long timeOfAuthorization, Integer expiredIn)
    {
        return (System.currentTimeMillis() - timeOfAuthorization) / 1000L > expiredIn;
    }
}
