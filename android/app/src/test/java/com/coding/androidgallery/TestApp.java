package com.coding.androidgallery;

import com.coding.androidgallery.modules.MockNetworkModule;

/**
 * Created by Mudassar Hussain on 5/30/2019.
 */
public class TestApp extends App {
    @Override
    public void setupAppComponent() {
        if(appComponent == null){
            appComponent = DaggerTestAppComponent.builder().mockNetworkModule(new MockNetworkModule()).build();
        }
    }
}
