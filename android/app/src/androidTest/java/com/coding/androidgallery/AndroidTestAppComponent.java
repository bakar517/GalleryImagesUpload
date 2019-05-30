package com.coding.androidgallery;

import com.coding.androidgallery.modules.AppModule;
import com.coding.androidgallery.modules.MockNetworkModule;
import com.coding.androidgallery.modules.TestDataModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Mudassar Hussain on 6/2/2019.
 */
@Component(modules = {AppModule.class, MockNetworkModule.class, TestDataModule.class})
@Singleton
public interface AndroidTestAppComponent extends AppComponent{
}
