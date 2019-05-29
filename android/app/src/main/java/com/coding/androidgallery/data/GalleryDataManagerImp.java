package com.coding.androidgallery.data;

import com.coding.androidgallery.data.model.DeviceInfo;
import com.coding.androidgallery.data.model.ImageUploadResponse;
import com.coding.androidgallery.data.model.User;
import com.coding.androidgallery.data.remote.RemoteGalleryRepository;
import com.coding.androidgallery.util.android.AndroidHelper;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by Mudassar Hussain on 5/26/2019.
 */
public class GalleryDataManagerImp implements GalleryDataManager {

    RemoteGalleryRepository galleryRepository;


    DeviceInfo deviceInfo;

    @Inject
    public GalleryDataManagerImp(RemoteGalleryRepository galleryRepository) {
        this.galleryRepository = galleryRepository;
    }

    @Override
    public Observable<ImageUploadResponse> uploadPhoto(String imageFilePath) {
        User user = getUserInfo();
        return galleryRepository.uploadPhoto(user.getUserId(),imageFilePath, deviceInfo);
    }

    @Override
    public User getUserInfo(){
        //Load LoggedIn user info from persistent storage. For time being I hardcoded it.
        return User.createUser("10");
    }
}
