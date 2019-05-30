package com.coding.androidgallery.data;

import com.coding.androidgallery.data.model.GalleryResponse;
import com.coding.androidgallery.data.model.UploadResponse;
import com.coding.androidgallery.data.model.User;


import io.reactivex.Observable;

/**
 * Created by Mudassar Hussain on 5/26/2019.
 */
public interface GalleryDataManager {
    Observable<UploadResponse> uploadPhoto(String imageFilePath);

    Observable<GalleryResponse> fetchAll(long lastSeen);

    User getUserInfo();

}
