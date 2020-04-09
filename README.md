# Spotify-API-Wrapper
An intuitive wrapper over the [Spotify Developer API](https://developer.spotify.com/documentation/web-api/reference/)

## Integrating with your App

Maven
```xml
<dependency>
    <groupId>com.github.lajospolya</groupId>
    <artifactId>spotify-api-wrapper</artifactId>
    <version>1.0.RELEASE</version>
</dependency>
```

Gradle
```gradle
dependencies {
    implementation group: 'com.github.lajospolya', name: 'spotify-api-wrapper', version: '1.0.RELEASE'
}
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
    .createAuthorizationFlowClient(CLIENT_ID, CLIENT_SECRET, CODE, REDIRECT_URL);
```

#### Requests

All requests extend `AbstractSpotifyRequest<?>`

Requests are built using the [Builder Pattern](https://en.wikipedia.org/wiki/Builder_pattern). All parameters for the `Builder` 
constructor are mandatory to the request. All appended parameters (ex, market) are optional parameters.
 
 for example

```java
String TRACK_ID = "1EaKU4dMbesXXd3BrLCtYG";
String MARKET = "CA";
GetTrack getTrackRequest = new GetTrack.Builder(TRACK_ID)
    .market(MARKET).build();
```

#### Sending a Synchronous Request (full example)
The responses for all requests are typed.

```java
SpotifyApiClient client = SpotifyApiClient
    .createAuthorizationFlowClient(CLIENT_ID, CLIENT_SECRET, CODE, REDIRECT_URL);

String TRACK_ID = "1EaKU4dMbesXXd3BrLCtYG";
String MARKET = "CA";
GetTrack getTrackRequest = new GetTrack.Builder(TRACK_ID)
    .market(MARKET).build();

Track track = client.sendRequest(getTrackRequest);
```

#### Sending an Asynchronous Request (full example)
```java
String SHOW_ID = "4xdoysfv0ztl97lrj8Sg4W";
String MARKET = "CA";
GetShowsEpisodes getShowsRequest = new GetShowsEpisodes.Builder(SHOW_ID)
    .market(MARKET).limit(50).offset(3).build();
Paging<SimplifiedEpisode> episodes = client.sendRequestAsync(getShowsRequest).get();

```
#### Reauthorizing an Expired Client
```java
SpotifyApiClient client = SpotifyApiClient
    .createAuthorizationFlowClient(CLIENT_ID, CLIENT_SECRET, CODE, REDIRECT_URL);
 
// one method call to reauthorize the client
client.reauthorize();
// or asynchronously
client.reauthorizeAsync().get();
```

#### Caching with Etags
Spotify has implemented [Contitional Requests](https://developer.spotify.com/documentation/web-api/#conditional-requests) 
through the use of [ETags](https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/ETag).

Requests which support the use of ETags have an `etag` setter method. 
Responses which support the use of ETags have a `getEtag` getter method. 
If the ETag is set and the resource being requested hasn't changed then a null object will be returned.
```java
SpotifyApiClient client = SpotifyApiClient
    .createAuthorizationFlowClient(CLIENT_ID, CLIENT_SECRET, CODE, REDIRECT_URL);

GetMeTracks getUsersTracksRequest = new GetMeTracks.Builder()
        .offset(0).limit(50).build();
// Fetch my saved Tracks
Paging<SavedTrack> tracks = client.sendRequest(getUsersTracksRequest);

String etag = tracks.getEtag();

GetMeTracks getUsersTracksCachedRequest = new GetMeTracks.Builder()
    .etag(etag) // set the etag
    .offset(0).limit(50).build();
// If I haven't saved any tracks then tracksCached will be null
Paging<SavedTrack> tracksCached = client.sendRequest(getUsersTracksCachedRequest);
```
