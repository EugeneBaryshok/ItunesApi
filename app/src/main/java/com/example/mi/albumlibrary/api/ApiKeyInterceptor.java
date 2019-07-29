package com.example.mi.albumlibrary.api;

import android.support.annotation.NonNull;

import com.example.mi.albumlibrary.BuildConfig;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ApiKeyInterceptor implements Interceptor {
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        HttpUrl url = request.url().newBuilder()
                .build();
        request = request.newBuilder().url(url).build();
        return chain.proceed(request);
    }
}
