package com.coding.androidgallery.data.remote.api;

import com.coding.androidgallery.data.model.GalleryResponse;
import com.coding.androidgallery.data.model.UploadResponse;

import io.reactivex.Observable;
import okhttp3.RequestBody;

/**
 * Created by Mudassar Hussain on 5/28/2019.
 */
public class MockGalleryApiService implements GalleryApiService {
    UploadResponse mockResponse;
    GalleryResponse mockGalleryResponse;

    public MockGalleryApiService(){

    }

    public MockGalleryApiService(UploadResponse mockResponse){
        this.mockResponse = mockResponse;
    }

    public MockGalleryApiService(GalleryResponse mockResponse){
        mockGalleryResponse = mockResponse;
    }

    public MockGalleryApiService(UploadResponse response,GalleryResponse mockResponse){
        this.mockResponse = response;
        mockGalleryResponse = mockResponse;
    }


    @Override
    public Observable<UploadResponse> uploadPhoto(RequestBody requestBody) {
        if(mockResponse != null){
            return Observable.just(mockResponse);
        }else{
            return Observable.error(new Exception("Throw Expected Exception!"));
        }
    }

    @Override
    public Observable<GalleryResponse> fetchAll(String userId, long lastSeen) {
        if(mockGalleryResponse != null){
            return Observable.just(mockGalleryResponse);
        }else{
            return Observable.error(new Exception("Throw Expected Exception!"));
        }
    }
}
