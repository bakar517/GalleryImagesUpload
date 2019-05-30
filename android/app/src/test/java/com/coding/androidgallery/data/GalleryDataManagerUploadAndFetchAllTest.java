package com.coding.androidgallery.data;

import com.coding.androidgallery.DaggerTestAppComponent;
import com.coding.androidgallery.TestAppComponent;
import com.coding.androidgallery.data.model.DeviceInfo;
import com.coding.androidgallery.data.model.GalleryResponse;
import com.coding.androidgallery.data.model.MockResponseFactory;
import com.coding.androidgallery.data.model.UploadResponse;
import com.coding.androidgallery.data.model.User;
import com.coding.androidgallery.data.remote.RemoteGalleryRepository;
import com.coding.androidgallery.modules.MockNetworkModule;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


/**
 * Created by Mudassar Hussain on 5/27/2019.
 */

public class GalleryDataManagerUploadAndFetchAllTest {

    @Inject
    RemoteGalleryRepository remoteGalleryRepository;

    @Inject
    DeviceInfo deviceInfo;

    @Inject
    GalleryDataManager dataManager;


    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void uploadPhoto_WithFileAndUserInfo_ShouldGetResponse(){
        String imageFilePath = "test.png";
        setupMockResponseWithDI(MockResponseFactory.mockUploadResponse());
        TestObserver<UploadResponse> observer = dataManager.uploadPhoto(imageFilePath).test();
        observer.awaitTerminalEvent();
        observer.assertNoErrors();
        observer.assertValueCount(1);
        observer.assertComplete();
    }

    @Test
    public void fetchAllPhotos_WithUserInfo_ShouldGetResponse(){
        long lastSeen = 0;

        setupMockResponseWithDI(MockResponseFactory.mockGalleryResponse());
        TestObserver<GalleryResponse> observer = dataManager.fetchAll(lastSeen).test();
        observer.awaitTerminalEvent();
        observer.assertNoErrors();
        observer.assertValueCount(1);
        observer.assertComplete();
    }

    private void setupMockResponseWithDI(UploadResponse mock){
        TestAppComponent appComponent = DaggerTestAppComponent.builder().mockNetworkModule(new MockNetworkModule(mock)).build();
        appComponent.inject(this);
    }

    private void setupMockResponseWithDI(GalleryResponse mock){
        TestAppComponent appComponent = DaggerTestAppComponent.builder().mockNetworkModule(new MockNetworkModule(mock)).build();
        appComponent.inject(this);
    }
}
