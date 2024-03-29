package com.coding.androidgallery.data.remote;

import com.coding.androidgallery.DaggerTestAppComponent;
import com.coding.androidgallery.TestAppComponent;
import com.coding.androidgallery.data.model.DeviceInfo;
import com.coding.androidgallery.data.model.UploadResponse;
import com.coding.androidgallery.modules.MockNetworkModule;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import javax.inject.Inject;

import io.reactivex.observers.TestObserver;

import static org.mockito.Mockito.mock;

/**
 * Created by Mudassar Hussain on 5/27/2019.
 */
public class RemoteGalleryRepositoryUploadTest {

    @Inject
    RemoteGalleryRepository galleryRepository;


    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void uploadPhoto_WithMockServiceImp_ShouldReturnSuccess(){
        String userId = "1";
        String imageFilePath = "test.png";
        setupMockResponseWithDI(mock(UploadResponse.class));
        DeviceInfo deviceInfo = mock(DeviceInfo.class);
        TestObserver<UploadResponse> observer = galleryRepository.uploadPhoto(userId,imageFilePath,deviceInfo).test();
        observer.awaitTerminalEvent();
        observer.assertNoErrors();
        observer.assertValueCount(1);
        observer.assertComplete();
    }

    @Test
    public void uploadPhoto_WithMockServiceImp_ShouldThrowException(){
        String userId = "1";
        String imageFilePath = "test.png";
        DeviceInfo deviceInfo = mock(DeviceInfo.class);
        setupMockResponseWithDI(null);
        TestObserver<UploadResponse> observer = galleryRepository.uploadPhoto(userId,imageFilePath,deviceInfo).test();
        observer.awaitTerminalEvent();
        observer.assertError(Exception.class);
        observer.assertValueCount(0);
    }

    private void setupMockResponseWithDI(UploadResponse mock){
        TestAppComponent appComponent = DaggerTestAppComponent.builder().mockNetworkModule(new MockNetworkModule(mock)).build();
        appComponent.inject(this);
    }
}
