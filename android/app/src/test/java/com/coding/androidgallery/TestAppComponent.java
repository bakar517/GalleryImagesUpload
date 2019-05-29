package com.coding.androidgallery;

import com.coding.androidgallery.modules.AppModule;
import com.coding.androidgallery.modules.DataModule;
import com.coding.androidgallery.modules.MockNetworkModule;
import com.coding.androidgallery.modules.TestDataModule;
import com.coding.androidgallery.ui.main.GalleryActivityPresenterTest;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Mudassar Hussain on 5/30/2019.
 */
@Component(modules = {AppModule.class, MockNetworkModule.class, TestDataModule.class})
@Singleton
public interface TestAppComponent extends AppComponent{
    void inject(GalleryActivityPresenterTest activityPresenterTest);
}
