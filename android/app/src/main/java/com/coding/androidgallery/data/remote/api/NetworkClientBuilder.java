package com.coding.androidgallery.data.remote.api;

import android.content.Context;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Mudassar Hussain on 5/26/2019.
 */
public final class NetworkClientBuilder {

    private final long CACHE_SIZE = 20*1024*1024;

    private final int CONNECTION_TIMEOUT = 20;

    private final int READ_WRITE_TIMEOUT = 20;

    private String baseUrl;
    private OkHttpClient client;

    public NetworkClientBuilder(Context context, String url){
        client = createHttpClient(context);
        baseUrl = url;
    }

    public NetworkClientBuilder addInterceptor(Interceptor interceptor){
        client = client.newBuilder().addInterceptor(interceptor).build();
        return this;
    }

    public Retrofit build(){
        return createRetrofit();
    }

    private OkHttpClient createHttpClient(Context context){
        Cache httpCache = new Cache(new File(context.getCacheDir(),"http-cache"),CACHE_SIZE);
        return new OkHttpClient.Builder().cache(httpCache)
                .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_WRITE_TIMEOUT,TimeUnit.SECONDS)
                .writeTimeout(READ_WRITE_TIMEOUT,TimeUnit.SECONDS)
                .build();
    }

    private Retrofit createRetrofit(){
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .baseUrl(baseUrl)
                .build();
    }
}