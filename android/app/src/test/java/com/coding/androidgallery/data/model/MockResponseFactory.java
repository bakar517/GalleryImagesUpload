package com.coding.androidgallery.data.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mudassar Hussain on 6/3/2019.
 */
public class MockResponseFactory {
    public static GalleryResponse mockGalleryResponse(){
        GalleryResponse response = new GalleryResponse(GalleryResponse.SUCCESS_CODE,"Mock Response");
        response.setHasMore(false);
        List<GalleryImage> list = new ArrayList<>();
        list.add(mockObject(1));
        list.add(mockObject(2));
        response.setGalleryList(list);
        return response;
    }

    public static GalleryImage mockObject(long id){
        return new GalleryImage(id,"test.png","05/05/1989","{}");
    }

    public static GalleryResponse mockGalleryResponseForError(){
        GalleryResponse mockResponse = new GalleryResponse(GalleryResponse.ERROR_CODE,"Invalid file type!");
        return mockResponse;
    }

    public static UploadResponse mockUploadResponse(){
        UploadResponse mockResponse = new UploadResponse(UploadResponse.SUCCESS_CODE,"Uploaded");
        GalleryImage item = new GalleryImage(1,"test.png","05/05/1989","{}");
        mockResponse.setItem(item);
        return mockResponse;
    }

    public static UploadResponse mockUploadResponseForError(){
        UploadResponse mockResponse = new UploadResponse(UploadResponse.ERROR_CODE,"Invalid file type!");
        return mockResponse;
    }
}
