package com.coding.androidgallery.data.remote;

import com.coding.androidgallery.data.model.DeviceInfo;
import com.coding.androidgallery.data.model.UploadResponse;
import com.coding.androidgallery.data.remote.api.MockGalleryApiService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import io.reactivex.observers.TestObserver;

import static org.mockito.Mockito.mock;

/**
 * Created by Mudassar Hussain on 5/27/2019.
 */
public class RemoteGalleryRepositoryUploadTest {

    RemoteGalleryRepository galleryRepository;


    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void uploadPhoto_WithMockServiceImp_ShouldReturnSuccess(){
        String userId = "1";
        String imageFilePath = "test.png";
        galleryRepository = new RemoteGalleryRepositoryImp(new MockGalleryApiService(mock(UploadResponse.class)));
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
        galleryRepository = new RemoteGalleryRepositoryImp(new MockGalleryApiService());
        TestObserver<UploadResponse> observer = galleryRepository.uploadPhoto(userId,imageFilePath,deviceInfo).test();
        observer.awaitTerminalEvent();
        observer.assertError(Exception.class);
        observer.assertValueCount(0);
    }
}
