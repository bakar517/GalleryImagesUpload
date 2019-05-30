package com.coding.androidgallery.data.remote;

import com.coding.androidgallery.data.model.DeviceInfo;
import com.coding.androidgallery.data.model.GalleryResponse;
import com.coding.androidgallery.data.model.UploadResponse;
import com.coding.androidgallery.data.remote.api.GalleryApiService;
import com.coding.androidgallery.data.remote.api.MockGalleryApiService;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import okhttp3.RequestBody;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by Mudassar Hussain on 5/27/2019.
 */
public class RemoteGalleryRepositoryTest {

    @Mock
    GalleryApiService apiService;

    RemoteGalleryRepository galleryRepository;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        galleryRepository = new RemoteGalleryRepositoryImp(apiService);
    }

    @After
    public void release(){
        galleryRepository = null;
    }

    @Test
    public void uploadPhoto_WithMockServiceImp_ShouldReturnSuccess(){
        String userId = "1";
        String imageFilePath = "test.png";

        DeviceInfo deviceInfo = mock(DeviceInfo.class);
        UploadResponse response = mock(UploadResponse.class);
        Mockito.when(apiService.uploadPhoto(any(RequestBody.class)))
                .thenReturn(Observable.just(response));

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
        Exception exception = new Exception("Unable to make request!");
        Mockito.when(apiService.uploadPhoto(any(RequestBody.class)))
                .thenReturn(Observable.<UploadResponse>error(exception));
        TestObserver<UploadResponse> observer = galleryRepository.uploadPhoto(userId,imageFilePath,deviceInfo).test();
        observer.awaitTerminalEvent();
        observer.assertError(Exception.class);
        observer.assertValueCount(0);
    }

    @Test
    public void fetchAll_WithMockServiceImp_ShouldReturnSuccess(){
        String userId = "1";
        long lastSeen = 0;
        GalleryResponse response = mock(GalleryResponse.class);
        Mockito.when(apiService.fetchAll(anyString(),anyLong()))
                .thenReturn(Observable.just(response));

        TestObserver<GalleryResponse> observer = galleryRepository.fetchAll(userId,lastSeen).test();
        observer.awaitTerminalEvent();
        observer.assertNoErrors();
        observer.assertValueCount(1);
        observer.assertComplete();
    }

    @Test
    public void fetchAll_WithMockServiceImp_ShouldThrowException(){
        String userId = "1";
        long lastSeen = 0;
        Exception exception = new Exception("Unable to make request!");
        Mockito.when(apiService.fetchAll(anyString(),anyLong()))
                .thenReturn(Observable.<GalleryResponse>error(exception));
        TestObserver<GalleryResponse> observer = galleryRepository.fetchAll(userId,lastSeen).test();
        observer.awaitTerminalEvent();
        observer.assertError(Exception.class);
        observer.assertValueCount(0);
    }
}
