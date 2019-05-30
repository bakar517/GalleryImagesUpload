package com.coding.androidgallery.data.remote.api;

import android.content.Context;

import com.squareup.picasso.OkHttp3Downloader;

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
 * NetworkClientBuilder is used to create Retrofit and OkHttp clients.
 * It uses Builder pattern to create object with different configurations.
 */
public final class NetworkClientBuilder {


    /**
     * HttpClientBuilder provide OkHttp3Downloader with optional params like cache.
     */
    public static class HttpClientBuilder{

        final long CACHE_SIZE = 20*1024*1024;

        private OkHttpClient client;
        private Context context;

        /**
         * @param context
         */
        public HttpClientBuilder(Context context){
            this.context = context;
            client = createHttpClient(context,CACHE_SIZE);
        }

        /**
         * @param cacheSize
         * @return HttpClientBuilder
         */
        public HttpClientBuilder cache(long cacheSize){
            if(cacheSize > CACHE_SIZE){
                client = createHttpClient(context,cacheSize);
            }
            return this;
        }

        /**
         * @return OkHttp3Downloader
         */
        public OkHttp3Downloader build(){
            return new OkHttp3Downloader(client);
        }


        /**
         * @param context
         * @param cacheSize
         * @return OkHttpClient
         */
        private OkHttpClient createHttpClient(Context context,long cacheSize){
            Cache httpCache = new Cache(new File(context.getCacheDir(),"http-cache"),cacheSize);
            return new OkHttpClient.Builder().cache(httpCache).build();
        }

    }


    /**
     * RetrofitBuilder provide Retrofit client with optional params like interceptors etc.
     */

     public static class RetrofitBuilder{
        private final int CONNECTION_TIMEOUT = 20;

        private final int READ_WRITE_TIMEOUT = 20;

        private String baseUrl;
        private OkHttpClient client;

        public RetrofitBuilder(String url){
            client = createHttpClient();
            baseUrl = url;
        }

        public RetrofitBuilder addInterceptor(Interceptor interceptor){
            client = client.newBuilder().addInterceptor(interceptor).build();
            return this;
        }

        public Retrofit build(){
            return createRetrofit();
        }

        /**
         * @return OkHttpClient
         */

        private OkHttpClient createHttpClient(){
            return new OkHttpClient.Builder()
                    .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(READ_WRITE_TIMEOUT,TimeUnit.SECONDS)
                    .writeTimeout(READ_WRITE_TIMEOUT,TimeUnit.SECONDS)
                    .build();
        }

        /**
         * @return Retrofit
         */
        private Retrofit createRetrofit(){
            return new Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .baseUrl(baseUrl)
                    .build();
        }
    }
}