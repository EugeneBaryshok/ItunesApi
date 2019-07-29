package com.example.mi.albumlibrary.repository;

import android.support.annotation.NonNull;

public class RepositoryProvider {
    private static AlbumRepository sAlbumRepository;

    @NonNull
    public static AlbumRepository provideRepository() {
        if (sAlbumRepository == null) {
            sAlbumRepository = new DefaultAlbumRepository();
        }
        return sAlbumRepository;
    }
}
