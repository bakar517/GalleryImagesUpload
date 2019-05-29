package com.coding.androidgallery.modules;

import com.coding.androidgallery.data.GalleryDataManager;
import com.coding.androidgallery.data.GalleryDataManagerImp;
import com.coding.androidgallery.data.model.DeviceInfo;
import com.coding.androidgallery.data.remote.RemoteGalleryRepository;
import com.coding.androidgallery.data.remote.RemoteGalleryRepositoryImp;
import com.coding.androidgallery.data.remote.api.GalleryApiService;
import com.coding.androidgallery.util.android.AndroidHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Mudassar Hussain on 5/26/2019.
 */
@Module
public class DataModule {

    @Provides
    @Singleton
    RemoteGalleryRepository remoteGalleryRepository(GalleryApiService service){
        return new RemoteGalleryRepositoryImp(service);
    }

    @Provides
    @Singleton
    GalleryDataManager galleryDataManager(RemoteGalleryRepository repository){
        return new GalleryDataManagerImp(repository);
    }

    @Provides
    DeviceInfo getDeviceInfo(){
        return AndroidHelper.getDeviceInfo();
    }
}
