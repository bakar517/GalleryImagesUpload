package com.coding.androidgallery.data.remote;

import com.coding.androidgallery.data.model.DeviceInfo;
import com.coding.androidgallery.data.model.ImageUploadResponse;

import io.reactivex.Observable;

/**
 * Created by Mudassar Hussain on 5/26/2019.
 */
public interface RemoteGalleryRepository {
    Observable<ImageUploadResponse> uploadPhoto(String userId, String imageFilePath, DeviceInfo deviceInfo);
}
