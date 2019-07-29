package com.example.mi.albumlibrary.screen.tracks;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.mi.albumlibrary.models.content.Album;
import com.example.mi.albumlibrary.repository.RepositoryProvider;

import static com.example.mi.albumlibrary.models.content.Album.getAlbumById;


@InjectViewState
public class AlbumDetailPresenter extends MvpPresenter<AlbumDetailView> {

    private final AlbumDetailView mView;
    private int mId;
    private Album album;

    public AlbumDetailPresenter() {
        this.mView = getViewState();
    }
    public AlbumDetailPresenter(AlbumDetailView mView) {
        this.mView = mView;
    }

    public void init(int id) {
        this.mId = id;
        album = getAlbumById(id);
        mView.setFieldsOnInit(album);

        RepositoryProvider.provideRepository()
                .getAlbumTracks(id)
                .doOnSubscribe(mView::showLoading)
                .doOnTerminate(mView::hideLoading)
                .subscribe(mView::showTracks, throwable -> mView.showError());
    }
}
