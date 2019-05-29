package com.coding.androidgallery.data.remote.api;

import com.coding.androidgallery.data.model.ImageUploadResponse;

import io.reactivex.Observable;
import okhttp3.RequestBody;

/**
 * Created by Mudassar Hussain on 5/28/2019.
 */
public class MockGalleryApiService implements GalleryApiService {
    ImageUploadResponse mockResponse;

    public MockGalleryApiService(){

    }

    public MockGalleryApiService(ImageUploadResponse mockResponse){
        setMockResponse(mockResponse);
    }

    public void setMockResponse(ImageUploadResponse mockResponse){
        this.mockResponse = mockResponse;
    }

    @Override
    public Observable<ImageUploadResponse> uploadPhoto(RequestBody requestBody) {
        if(mockResponse != null){
            return Observable.just(mockResponse);
        }else{
            return Observable.error(new Exception("Network is down!"));
        }
    }
}
