package com.coding.androidgallery.modules;

import com.coding.androidgallery.data.model.UploadResponse;
import com.coding.androidgallery.data.remote.api.GalleryApiService;
import com.coding.androidgallery.data.remote.api.MockGalleryApiService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Mudassar Hussain on 5/28/2019.
 */
@Module
public class MockNetworkModule{
    UploadResponse mockResponse;

    public MockNetworkModule(){

    }

    public MockNetworkModule(UploadResponse mockResponse){
        this.mockResponse = mockResponse;
    }

    @Provides
    @Singleton
    GalleryApiService getMockService(){
        return new MockGalleryApiService(mockResponse);
    }
}
