package com.coding.androidgallery.data.remote;

import com.coding.androidgallery.DaggerTestAppComponent;
import com.coding.androidgallery.TestAppComponent;
import com.coding.androidgallery.data.model.DeviceInfo;
import com.coding.androidgallery.data.model.GalleryResponse;
import com.coding.androidgallery.data.model.UploadResponse;
import com.coding.androidgallery.data.remote.api.MockGalleryApiService;
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
public class RemoteGalleryRepositoryFetchAllTest {

    @Inject
    RemoteGalleryRepository galleryRepository;


    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void fetchAll_WithMockServiceImp_ShouldReturnSuccess(){
        String userId = "1";
        long lastSeen = 0;
        setupMockResponseWithDI(mock(GalleryResponse.class));
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
        setupMockResponseWithDI(null);
        TestObserver<GalleryResponse> observer = galleryRepository.fetchAll(userId,lastSeen).test();
        observer.awaitTerminalEvent();
        observer.assertError(Exception.class);
        observer.assertValueCount(0);
    }

    private void setupMockResponseWithDI(GalleryResponse mock){
        TestAppComponent appComponent = DaggerTestAppComponent.builder().mockNetworkModule(new MockNetworkModule(mock)).build();
        appComponent.inject(this);
    }
}
