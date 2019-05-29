package com.coding.androidgallery.data.remote.api;

import com.coding.androidgallery.data.model.UploadResponse;

import io.reactivex.Observable;
import okhttp3.RequestBody;

/**
 * Created by Mudassar Hussain on 5/28/2019.
 */
public class MockGalleryApiService implements GalleryApiService {
    UploadResponse mockResponse;

    public MockGalleryApiService(){

    }

    public MockGalleryApiService(UploadResponse mockResponse){
        setMockResponse(mockResponse);
    }

    public void setMockResponse(UploadResponse mockResponse){
        this.mockResponse = mockResponse;
    }

    @Override
    public Observable<UploadResponse> uploadPhoto(RequestBody requestBody) {
        if(mockResponse != null){
            return Observable.just(mockResponse);
        }else{
            return Observable.error(new Exception("Network is down!"));
        }
    }
}
