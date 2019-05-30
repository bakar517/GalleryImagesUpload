package com.coding.androidgallery.data.remote.api;

import com.coding.androidgallery.data.model.GalleryImage;
import com.coding.androidgallery.data.model.GalleryResponse;
import com.coding.androidgallery.data.model.MockResponseFactory;
import com.coding.androidgallery.data.model.UploadResponse;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import okhttp3.RequestBody;

import static org.mockito.Mockito.mock;

/**
 * Created by Mudassar Hussain on 5/28/2019.
 */
public class MockGalleryApiServiceTest {

    private MockGalleryApiService getMockService(UploadResponse mockResponse){
        return new MockGalleryApiService(mockResponse);
    }

    private MockGalleryApiService getMockService(GalleryResponse mockResponse){
        return new MockGalleryApiService(mockResponse);
    }

    @Test
    public void uploadPhoto_ValidInput_ShouldUploadSuccessfully() {
        UploadResponse mockResponse = MockResponseFactory.mockUploadResponse();
        MockGalleryApiService galleryApiService = getMockService(mockResponse);
        RequestBody requestBody = mock(RequestBody.class);
        UploadResponse response = galleryApiService.uploadPhoto(requestBody).blockingFirst();
        Assert.assertNotNull(response);
        Assert.assertEquals(mockResponse.getItem(),response.getItem());
        Assert.assertEquals(mockResponse.getStatusCode(),response.getStatusCode());
        Assert.assertEquals(mockResponse,response);
    }

    @Test
    public void uploadPhoto_ValidInput_ShouldReturnError() {
        UploadResponse mockResponse = MockResponseFactory.mockUploadResponseForError();
        MockGalleryApiService galleryApiService = getMockService(mockResponse);
        RequestBody requestBody = mock(RequestBody.class);
        UploadResponse response = galleryApiService.uploadPhoto(requestBody).blockingFirst();
        Assert.assertNotNull(response);
        Assert.assertEquals(mockResponse.getStatusCode(),response.getStatusCode());
        Assert.assertEquals(mockResponse,response);
    }

    @Test(expected= Exception.class)
    public void uploadPhoto_InternetNotAvailable_ShouldThrowException() {
        MockGalleryApiService galleryApiService = getMockService((UploadResponse) null);
        RequestBody requestBody = mock(RequestBody.class);
        galleryApiService.uploadPhoto(requestBody).blockingFirst();
    }

    @Test
    public void fetchAll_WithUserID_ShouldReturnList() {
        GalleryResponse mockResponse = MockResponseFactory.mockGalleryResponse();
        MockGalleryApiService galleryApiService = getMockService(mockResponse);
        String userId = "1";
        long lastSeen = 0;
        GalleryResponse response = galleryApiService.fetchAll(userId,lastSeen).blockingFirst();
        Assert.assertNotNull(response);
        Assert.assertFalse(response.hasMore());
        Assert.assertEquals(mockResponse.getStatusCode(),response.getStatusCode());
        Assert.assertEquals(mockResponse,response);
    }

    @Test(expected= Exception.class)
    public void fetchAll_InternetNotAvailable_ShouldThrowException() {
        MockGalleryApiService galleryApiService = getMockService((GalleryResponse) null);
        String userId = "1";
        long lastSeen = 0;
        galleryApiService.fetchAll(userId,lastSeen).blockingFirst();
    }



}
