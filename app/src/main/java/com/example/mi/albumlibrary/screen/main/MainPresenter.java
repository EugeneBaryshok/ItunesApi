package com.example.mi.albumlibrary.screen.main;

import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.mi.albumlibrary.models.content.Album;
import com.example.mi.albumlibrary.repository.RepositoryProvider;

import java.util.ArrayList;
import java.util.List;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> implements AlbumItemClickListener {
    private MainView mView;

    public MainPresenter(@NonNull MainView view) {
        mView = view;
    }
    public MainPresenter() {}

    public void init() {

        RepositoryProvider.provideRepository()
                .getAlbums()
                .doOnSubscribe(mView::showLoading)
                .doOnTerminate(mView::hideLoading)
                .subscribe(mView::showAlbums, throwable -> mView.showError());

    }

    @Override
    public void onAlbumClick(Album album, ImageView albumImage) {
        mView.showDetails(album, albumImage);
    }

    public void searchAlbum(String inputText, List<Album> albumList)
    {
        String userInput = inputText.toLowerCase();
        List<Album> newAlbumList = new ArrayList<>();

        for (Album album : albumList) {

            if (album.getmName().toLowerCase().contains(userInput)) {
                newAlbumList.add(album);
            }
        }
        mView.updateAlbumList(newAlbumList);

    }
}
