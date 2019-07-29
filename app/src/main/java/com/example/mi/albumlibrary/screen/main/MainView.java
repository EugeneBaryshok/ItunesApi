package com.example.mi.albumlibrary.screen.main;

import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.arellomobile.mvp.MvpView;
import com.example.mi.albumlibrary.models.content.Album;
import com.example.mi.albumlibrary.screen.general.LoadingView;

import java.util.List;

public interface MainView extends LoadingView, MvpView {

    void showError();

    void showAlbums(List<Album> albums);

    void showDetails(@NonNull Album album, @NonNull ImageView imageView);

    void updateAlbumList(List<Album> newAlbumList);
}
