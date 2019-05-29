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
        if(appComponent == null){
            appComponent = DaggerAppComponent.builder().build();
        }
    }

    public static App getInstance(){
        return instance;
    }
}
