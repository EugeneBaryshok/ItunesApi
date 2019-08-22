package com.example.mi.albumlibrary.screen.main;

import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ImageView;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.mi.albumlibrary.models.content.Album;
import com.example.mi.albumlibrary.repository.BasePresenter;
import com.example.mi.albumlibrary.repository.RepositoryProvider;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;

@InjectViewState
public class MainPresenter extends BasePresenter<MainView> implements AlbumItemClickListener {

    public MainPresenter() {
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        init();
    }

    public void init() {

        Subscription subscription = RepositoryProvider.provideRepository()
                .getAlbums()
                .doOnSubscribe(getViewState()::showLoading)
                .doOnTerminate(getViewState()::hideLoading)
                .subscribe(getViewState()::showAlbums, throwable -> getViewState().showError());
        unsubscribeOnDestroy(subscription);
    }

    @Override
    public void onAlbumClick(Album album, ImageView albumImage) {
        getViewState().showDetails(album, albumImage);
    }

    public void searchAlbum(String inputText, List<Album> albumList) {
        String userInput = inputText.toLowerCase();
        List<Album> newAlbumList = new ArrayList<>();

        for (Album album : albumList) {

            if (album.getmName().toLowerCase().contains(userInput)) {
                newAlbumList.add(album);
            }
        }
        getViewState().updateAlbumList(newAlbumList);

    }
}
