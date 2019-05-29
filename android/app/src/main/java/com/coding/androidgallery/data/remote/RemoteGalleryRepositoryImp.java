package com.coding.androidgallery.data.remote;

import com.coding.androidgallery.data.model.DeviceInfo;
import com.coding.androidgallery.data.model.ImageUploadResponse;
import com.coding.androidgallery.data.remote.api.GalleryApiService;

import java.io.File;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by Mudassar Hussain on 5/26/2019.
 */
public class RemoteGalleryRepositoryImp implements RemoteGalleryRepository {

    GalleryApiService apiService;

    @Inject
    public RemoteGalleryRepositoryImp(GalleryApiService apiService) {
        this.apiService = apiService;
    }


    @Override
    public Observable<ImageUploadResponse> uploadPhoto(String userId, String imageFilePath, DeviceInfo deviceInfo) {
        File file = new File(imageFilePath);
        RequestBody fileRequestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part bodyPart = MultipartBody.Part.createFormData("file", file.getName(), fileRequestBody);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("userid", userId)
                .addFormDataPart("device_info", deviceInfo.toString())
                .addPart(bodyPart)
                .build();
        return apiService.uploadPhoto(requestBody).subscribeOn(Schedulers.io());
    }
}
