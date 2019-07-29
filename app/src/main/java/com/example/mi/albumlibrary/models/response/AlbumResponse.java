package com.example.mi.albumlibrary.models.response;

import android.support.annotation.NonNull;

import com.example.mi.albumlibrary.models.content.Album;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class AlbumResponse {
    @SerializedName("results")
    private List<Album> mAlbums;
    private int resultCount;

    @NonNull
    public List<Album> getAlbums() {
        if (mAlbums == null) {
            return new ArrayList<>();
        }
        return mAlbums;
    }
}
