package com.example.mi.albumlibrary.api;

import com.example.mi.albumlibrary.models.response.AlbumResponse;
import com.example.mi.albumlibrary.models.response.TrackResponse;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface MovieService {

    @GET("lookup?entity=song")
    Observable<TrackResponse> getAlbumTracks(@Query(value="id", encoded=true) int id);

    @GET("search?term=linkin+park&entity=album")
    Observable<AlbumResponse> getAlbums();


}
