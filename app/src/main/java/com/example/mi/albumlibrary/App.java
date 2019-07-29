package com.example.mi.albumlibrary;

import android.app.Application;
import android.support.annotation.NonNull;

import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.rx.RealmObservableFactory;

public class App extends Application {
    private static App sInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;

        Picasso picasso = new Picasso.Builder(this)
                .downloader(new OkHttp3Downloader(this))
                .build();
        Picasso.setSingletonInstance(picasso);

        Realm.init(getApplicationContext());
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .rxFactory(new RealmObservableFactory())
                .build();
        Realm.setDefaultConfiguration(configuration);
    }

    @NonNull
    public static App getAppContext() {
        return sInstance;
    }
}
