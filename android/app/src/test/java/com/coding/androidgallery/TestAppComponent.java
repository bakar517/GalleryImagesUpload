package com.coding.androidgallery;

import com.coding.androidgallery.data.GalleryDataManagerUploadAndFetchAllTest;
import com.coding.androidgallery.data.remote.RemoteGalleryRepositoryUploadTest;
import com.coding.androidgallery.data.remote.RemoteGalleryRepositoryFetchAllTest;
import com.coding.androidgallery.modules.AppModule;
import com.coding.androidgallery.modules.MockNetworkModule;
import com.coding.androidgallery.modules.TestDataModule;
import com.coding.androidgallery.ui.main.GalleryActivityUploadTest;
import com.coding.androidgallery.ui.main.GalleryPresenterUploadAndFetchTest;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Mudassar Hussain on 5/30/2019.
 */
@Component(modules = {AppModule.class, MockNetworkModule.class, TestDataModule.class})
@Singleton
public interface TestAppComponent extends AppComponent{
    void inject(GalleryPresenterUploadAndFetchTest activityPresenterTest);
    void inject(GalleryActivityUploadTest activityUploadTest);
    void inject(RemoteGalleryRepositoryFetchAllTest remoteGalleryRepositoryUploadTest);
    void inject(RemoteGalleryRepositoryUploadTest remoteGalleryRepositoryFetchAllTest);
    void inject(GalleryDataManagerUploadAndFetchAllTest galleryDataManagerUploadAndFetchAllTest);
}
