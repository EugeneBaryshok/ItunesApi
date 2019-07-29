package com.example.mi.albumlibrary.repository;

import android.support.annotation.NonNull;

import com.example.mi.albumlibrary.models.content.Album;
import com.example.mi.albumlibrary.models.content.Track;

import java.util.List;

import rx.Observable;

public interface AlbumRepository {

    @NonNull
    Observable<List<Track>> getAlbumTracks(int albumId);

    @NonNull
    Observable<List<Album>> getAlbums();

}
