package com.example.mi.albumlibrary.repository;

import android.support.annotation.NonNull;

import com.example.mi.albumlibrary.api.ApiFactory;
import com.example.mi.albumlibrary.models.content.Album;
import com.example.mi.albumlibrary.models.content.Track;
import com.example.mi.albumlibrary.models.response.AlbumResponse;
import com.example.mi.albumlibrary.models.response.TrackResponse;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import ru.arturvasilov.rxloader.RxUtils;
import rx.Observable;
import rx.functions.Func1;

public class DefaultAlbumRepository implements AlbumRepository {

    @NonNull
    @Override
    public Observable<List<Track>> getAlbumTracks(int albumId) {
//        final RxLoader rxLoader = new RxLoader(this, getSupportLoaderManager());
        return ApiFactory.getMoviesService()
                .getAlbumTracks(albumId)
                .map(TrackResponse::getTracks)
                .concatMap((Func1<List<Track>, Observable<Track>>) Observable::from)
                .filter(x -> x.getWrapperType().equals("track"))
                .toList()
                .flatMap(tracks -> {
                    Realm.getDefaultInstance().executeTransaction(realm -> {
                        realm.delete(Track.class);
                        realm.insert(tracks);
                    });
                    return Observable.just(tracks);
                })
                .onErrorResumeNext(throwable -> {
                    Realm realm = Realm.getDefaultInstance();
                    RealmResults<Track> movies = realm.where(Track.class).findAll();
                    return Observable.just(realm.copyFromRealm(movies));
                })
                .compose(RxUtils.async());
    }

    @NonNull
    @Override
    public Observable<List<Album>> getAlbums() {
        return ApiFactory.getMoviesService()
                .getAlbums()
                .map(AlbumResponse::getAlbums)
                .flatMap(albums -> {
                    Realm.getDefaultInstance().executeTransaction(realm -> {
                        realm.delete(Album.class);
                        realm.insert(albums);
                    });
                    return Observable.just(albums);
                })
                .onErrorResumeNext(throwable -> {
                    Realm realm = Realm.getDefaultInstance();
                    RealmResults<Album> albums = realm.where(Album.class).findAll();
                    return Observable.just(realm.copyFromRealm(albums));
                })
                .compose(RxUtils.async());
    }

}
