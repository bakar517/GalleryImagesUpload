package com.coding.androidgallery;

import com.coding.androidgallery.modules.AppModule;
import com.coding.androidgallery.modules.DataModule;
import com.coding.androidgallery.modules.NetworkModule;
import com.coding.androidgallery.ui.main.GalleryActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Mudassar Hussain on 5/26/2019.
 */
@Component(modules = {AppModule.class, NetworkModule.class, DataModule.class})
@Singleton
public interface AppComponent {
    void inject(GalleryActivity activity);
}
