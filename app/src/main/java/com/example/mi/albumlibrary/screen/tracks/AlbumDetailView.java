package com.example.mi.albumlibrary.screen.tracks;

import com.arellomobile.mvp.MvpView;
import com.example.mi.albumlibrary.models.content.Album;
import com.example.mi.albumlibrary.models.content.Track;
import com.example.mi.albumlibrary.screen.general.LoadingView;

import java.util.List;

public interface AlbumDetailView extends LoadingView, MvpView {

    void showError();

    void setFieldsOnInit(Album album);

    void showTracks(List<Track> trackList);
}