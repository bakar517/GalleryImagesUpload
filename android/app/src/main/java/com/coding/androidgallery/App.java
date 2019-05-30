package com.coding.androidgallery;

import android.app.Application;

/**
 * Created by Mudassar Hussain on 5/26/2019.
 */
public class App extends Application {
    private static App instance;

    public AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        setupAppComponent();
    }

    public void setupAppComponent(){
        appComponent = DaggerAppComponent.builder().build();
    }

    public void replaceAppComponent(AppComponent appComponent){
        this.appComponent = appComponent;
    }

    public void clearAppComponent(){
        setupAppComponent();
    }

    public static App getInstance(){
        return instance;
    }
}
