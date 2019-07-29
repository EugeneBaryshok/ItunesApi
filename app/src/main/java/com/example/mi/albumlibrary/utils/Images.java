package com.example.mi.albumlibrary.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.mi.albumlibrary.BuildConfig;

import com.example.mi.albumlibrary.R;
import com.example.mi.albumlibrary.models.content.Album;
import com.squareup.picasso.Picasso;

public final class Images {

    public static final String WIDTH_100 = "w100";
    public static final String WIDTH_185 = "w185";
    public static final String WIDTH_140 = "w140";
    public static final String WIDTH_780 = "w780";

    private Images() {
    }

    public static void loadMovie(@NonNull ImageView imageView, @NonNull Album album,
                                 @NonNull String size) {
        loadMovie(imageView, album.getmAlbumImage(), size);
    }

    public static void loadMovie(@NonNull ImageView imageView, @NonNull String posterPath,
                                 @NonNull String size) {
        String url = posterPath;
        Picasso.get()
                .load(url)
                .noFade()
                .into(imageView);
    }

    public static void loadFromDrawable(Context context, @NonNull ImageView imageView, int image) {
//        Picasso.with(context).load(image_array[position]).placeholder(R.drawable.ic_stub).into(imageView);
        Glide.with(context).load(image).into(imageView);
    }

    public static void fetch(@NonNull String posterPath, @NonNull String size) {
        String url = posterPath;
        Picasso.get()
                .load(url)
                .fetch();
    }
}