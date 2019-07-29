package com.example.mi.albumlibrary.screen.main;

import android.widget.ImageView;

import com.example.mi.albumlibrary.models.content.Album;

public interface AlbumItemClickListener {
    void onAlbumClick(Album album, ImageView movieImage); //we will need the imageview to make the shared animation between two activities

}
