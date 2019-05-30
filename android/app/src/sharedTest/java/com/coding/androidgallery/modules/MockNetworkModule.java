package com.coding.androidgallery.modules;

import com.coding.androidgallery.App;
import com.coding.androidgallery.data.model.GalleryResponse;
import com.coding.androidgallery.data.model.UploadResponse;
import com.coding.androidgallery.data.remote.api.GalleryApiService;
import com.coding.androidgallery.data.remote.api.MockGalleryApiService;
import com.coding.androidgallery.util.MockPhotoLoader;
import com.coding.androidgallery.util.PhotoLoader;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Mudassar Hussain on 5/28/2019.
 */
@Module
public class MockNetworkModule{
    UploadResponse mockResponse;
    GalleryResponse galleryResponse;

    public MockNetworkModule(){

    }

    public MockNetworkModule(UploadResponse mockResponse){
        this.mockResponse = mockResponse;
    }

    public MockNetworkModule(GalleryResponse mockResponse){
        this.galleryResponse = mockResponse;
    }

    public MockNetworkModule(UploadResponse mockResponse,GalleryResponse mockResponse1){
        this.mockResponse = mockResponse;
        this.galleryResponse = mockResponse1;
    }

    @Provides
    GalleryApiService getMockService(){
        return new MockGalleryApiService(mockResponse,galleryResponse);
    }

    @Provides
    @Singleton
    PhotoLoader photoLoader(App app){
        return new MockPhotoLoader();
    }
}
