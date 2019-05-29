package com.coding.androidgallery.modules;

import android.content.Context;

import com.coding.androidgallery.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Mudassar Hussain on 5/26/2019.
 */
@Module
public class AppModule {
    App instance;

    public AppModule(){
        instance = App.getInstance();
    }

    public AppModule(App instance){
        this.instance  = instance;
    }

    @Provides
    App getApp(){
        return instance;
    }

}
