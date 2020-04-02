# Spotify-API-Wrapper
An intuitive wrapper over the [Spotify Developer API](https://developer.spotify.com/documentation/web-api/reference/)

## Integrating with your App

Maven
```
<dependency>
    <groupId>com.github.lajospolya</groupId>
    <artifactId>spotify-api-wrapper</artifactId>
    <version>1.0.RELEASE</version>
</dependency>
```

Gradle
```
compile group: 'com.github.lajospolya', name: 'spotify-api-wrapper', version: '1.0.RELEASE'
```

For other Dependency Management systems please check [Maven](https://mvnrepository.com/artifact/com.github.lajospolya/spotify-api-wrapper/1.0.RELEASE)
or [Sonatype](https://search.maven.org/artifact/com.github.lajospolya/spotify-api-wrapper/1.0.RELEASE/jar) repositories.
 
 ## General Usage
 The API-Wrapper consists of two basic parts; the Client and the Requests.

#### Client
The Client is represented by the `SpotifyApiClient` class. The client can be authenticated using the 
[Client Credentials Flow](https://developer.spotify.com/documentation/general/guides/authorization-guide/#client-credentials-flow)
 or the 
 [Authorization Code Flow](https://developer.spotify.com/documentation/general/guides/authorization-guide/#authorization-code-flow) 
 via two static factory methods.

To read more about Spotify Authorization, please checkout [this link](https://developer.spotify.com/documentation/general/guides/authorization-guide/)

##### Client Credentials Flow

```java
SpotifyApiClient client = SpotifyApiClient
    .createClientCredentialsFlowClient(CLIENT_ID, CLIENT_SECRET);
```

##### Authorization Flow

```java
SpotifyApiClient client = SpotifyApiClient
    .createAuthorizationFlowClient(CLIENT_ID, CLIENT_SECRET, CODE, REDIRECT_URL;
```

#### Requests

All requests extend `AbstractSpotifyRequest<?>`

Requests are built using the `Builder Pattern`. All parameters for the `Builder` constructor are mandatory to the request.
All appended parameters (ex, market) are optional parameters.
 
 for example

```java
String TRACK_ID = "1EaKU4dMbesXXd3BrLCtYG";
String MARKET = "CA";
GetTrack getTrackRequest = new GetTrack.Builder(TRACK_ID)
    .market(MARKET).build();
```

#### Sending a Request (full example)
The responses for all requests are typed.

```java
SpotifyApiClient client = SpotifyApiClient
    .createAuthorizationFlowClient(CLIENT_ID, CLIENT_SECRET, CODE, REDIRECT_URL;

String TRACK_ID = "1EaKU4dMbesXXd3BrLCtYG";
String MARKET = "CA";
GetTrack getTrackRequest = new GetTrack.Builder(TRACK_ID)
    .market(MARKET).build();

Track track = client.sendRequest(getTrackRequest);
```


```
TODO: Reauthorization and Caching
```