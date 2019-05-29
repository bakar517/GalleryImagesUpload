package com.coding.androidgallery.modules;


import com.coding.androidgallery.App;
import com.coding.androidgallery.data.remote.api.GalleryApiService;
import com.coding.androidgallery.data.remote.api.NetworkClientBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

/**
 * Created by Mudassar Hussain on 5/26/2019.
 */
@Module
public class NetworkModule {

    private static final String BASE_URL =  "http://192.168.1.207/GalleryImagesUpload/backend/api/";

    private static final boolean NETWORK_LOGS = false;

    private static final String TAG = "NetworkLogs";


    HttpLoggingInterceptor loggingInterceptor(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                System.out.println(TAG.concat(" : ").concat(message));
            }
        });
        interceptor.setLevel(NETWORK_LOGS ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        return interceptor;
    }

    @Provides
    @Singleton
    GalleryApiService getApiService(App context){
        Retrofit retrofit = new NetworkClientBuilder(context,BASE_URL).addInterceptor(loggingInterceptor()).build();
        return retrofit.create(GalleryApiService.class);
    }
}
