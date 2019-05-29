package com.coding.androidgallery.data;

import com.coding.androidgallery.data.model.ImageUploadResponse;
import com.coding.androidgallery.data.model.User;


import io.reactivex.Observable;

/**
 * Created by Mudassar Hussain on 5/26/2019.
 */
public interface GalleryDataManager {
    Observable<ImageUploadResponse> uploadPhoto(String imageFilePath);

    User getUserInfo();

}
