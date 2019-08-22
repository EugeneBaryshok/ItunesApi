package com.example.mi.albumlibrary.screen.main;

import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.mi.albumlibrary.models.content.Album;
import com.example.mi.albumlibrary.screen.general.LoadingView;

import java.util.List;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface MainView extends LoadingView, MvpView {

    void showError();

    void showAlbums(List<Album> albums);

//    void setAlbums(List<Album> albums);


    @StateStrategyType(OneExecutionStateStrategy.class)
    void showDetails(@NonNull Album album, @NonNull ImageView imageView);

    void updateAlbumList(List<Album> newAlbumList);
}
