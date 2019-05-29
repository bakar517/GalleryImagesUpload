package com.coding.androidgallery.modules;

import com.coding.androidgallery.data.GalleryDataManager;
import com.coding.androidgallery.data.GalleryDataManagerImp;
import com.coding.androidgallery.data.model.DeviceInfo;
import com.coding.androidgallery.data.remote.RemoteGalleryRepository;
import com.coding.androidgallery.data.remote.RemoteGalleryRepositoryImp;
import com.coding.androidgallery.data.remote.api.GalleryApiService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static org.mockito.Mockito.mock;

/**
 * Created by Mudassar Hussain on 5/30/2019.
 */
@Module
public class TestDataModule {
    @Provides
    @Singleton
    RemoteGalleryRepository remoteGalleryRepository(GalleryApiService service){
        return new RemoteGalleryRepositoryImp(service);
    }

    @Provides
    @Singleton
    GalleryDataManager galleryDataManager(RemoteGalleryRepository repository, DeviceInfo deviceInfo){
        return new GalleryDataManagerImp(repository,deviceInfo);
    }

    @Provides
    DeviceInfo getDeviceInfo(){
        return mock(DeviceInfo.class);
    }
}
