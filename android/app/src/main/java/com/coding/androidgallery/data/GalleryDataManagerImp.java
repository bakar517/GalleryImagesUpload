package com.coding.androidgallery.data;

import com.coding.androidgallery.AppConfig;
import com.coding.androidgallery.data.model.DeviceInfo;
import com.coding.androidgallery.data.model.GalleryResponse;
import com.coding.androidgallery.data.model.UploadResponse;
import com.coding.androidgallery.data.model.User;
import com.coding.androidgallery.data.remote.RemoteGalleryRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by Mudassar Hussain on 5/26/2019.
 */
public class GalleryDataManagerImp implements GalleryDataManager {

    RemoteGalleryRepository galleryRepository;


    DeviceInfo deviceInfo;

    @Inject
    public GalleryDataManagerImp(RemoteGalleryRepository galleryRepository,DeviceInfo deviceInfo) {
        this.galleryRepository = galleryRepository;
        this.deviceInfo = deviceInfo;
    }

    @Override
    public Observable<UploadResponse> uploadPhoto(String imageFilePath) {
        User user = getUserInfo();
        return galleryRepository.uploadPhoto(user.getUserId(),imageFilePath, deviceInfo);
    }

    @Override
    public Observable<GalleryResponse> fetchAll(long lastSeen) {
        User user = getUserInfo();
        return galleryRepository.fetchAll(user.getUserId(),lastSeen);
    }

    @Override
    public User getUserInfo(){
        //Load LoggedIn user info from persistent storage. For time being I hardcoded it.
        return User.createUser(AppConfig.CURRENT_LOGGED_IN_USER_ID);
    }
}
