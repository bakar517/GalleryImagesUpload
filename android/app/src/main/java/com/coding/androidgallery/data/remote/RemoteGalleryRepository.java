package com.coding.androidgallery.data.remote;

import com.coding.androidgallery.data.model.DeviceInfo;
import com.coding.androidgallery.data.model.GalleryResponse;
import com.coding.androidgallery.data.model.UploadResponse;

import io.reactivex.Observable;

/**
 * Created by Mudassar Hussain on 5/26/2019.
 */
public interface RemoteGalleryRepository {
    Observable<UploadResponse> uploadPhoto(String userId, String imageFilePath, DeviceInfo deviceInfo);

    Observable<GalleryResponse> fetchAll(String userId, long lastSeen);
}
