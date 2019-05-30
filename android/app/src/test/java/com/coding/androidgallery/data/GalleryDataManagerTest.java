package com.coding.androidgallery.data;

import com.coding.androidgallery.data.model.DeviceInfo;
import com.coding.androidgallery.data.model.GalleryResponse;
import com.coding.androidgallery.data.model.UploadResponse;
import com.coding.androidgallery.data.model.User;
import com.coding.androidgallery.data.remote.RemoteGalleryRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import io.reactivex.Observable;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


/**
 * Created by Mudassar Hussain on 5/27/2019.
 */

public class GalleryDataManagerTest {

    @Mock
    RemoteGalleryRepository remoteGalleryRepository;

    @Mock
    DeviceInfo deviceInfo;

    GalleryDataManager dataManager;


    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        dataManager = new GalleryDataManagerImp(remoteGalleryRepository,deviceInfo);
    }

    @Test
    public void uploadPhoto_WithFileAndUserInfo_ShouldGetResponse(){
        User userInfo = dataManager.getUserInfo();
        String userId = userInfo.getUserId();
        String imageFilePath = "test.png";

        UploadResponse response = mock(UploadResponse.class);
        Mockito.when(remoteGalleryRepository.uploadPhoto(userId,imageFilePath,deviceInfo))
                .thenReturn(Observable.just(response));
        dataManager.uploadPhoto(imageFilePath);
        verify(remoteGalleryRepository).uploadPhoto(userId,imageFilePath,deviceInfo);
    }

    @Test
    public void fetchAllPhotos_WithUserInfo_ShouldGetResponse(){
        User userInfo = dataManager.getUserInfo();
        String userId = userInfo.getUserId();
        long lastSeen = 0;

        GalleryResponse response = mock(GalleryResponse.class);
        Mockito.when(remoteGalleryRepository.fetchAll(userId,lastSeen))
                .thenReturn(Observable.just(response));
        dataManager.fetchAll(lastSeen);
        verify(remoteGalleryRepository).fetchAll(userId,lastSeen);
    }
}
