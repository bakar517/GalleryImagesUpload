package com.coding.androidgallery.data.remote.api;

import com.coding.androidgallery.data.model.UploadResponse;

import org.junit.Assert;
import org.junit.Test;

import okhttp3.RequestBody;

import static org.mockito.Mockito.mock;

/**
 * Created by Mudassar Hussain on 5/28/2019.
 */
public class MockGalleryApiServiceTest {

    private MockGalleryApiService getMockService(UploadResponse mockResponse){
        return new MockGalleryApiService(mockResponse);
    }

    @Test
    public void uploadPhoto_ValidInput_ShouldUploadSuccesfully() {
        UploadResponse mockResponse = mock(UploadResponse.class);
        MockGalleryApiService galleryApiService = getMockService(mockResponse);
        RequestBody requestBody = mock(RequestBody.class);
        UploadResponse response = galleryApiService.uploadPhoto(requestBody).blockingFirst();
        Assert.assertNotNull(response);
        Assert.assertEquals(mockResponse,response);
    }

    @Test(expected= Exception.class)
    public void uploadPhoto_InternetNotAvailable_ShouldThrowException() {
        MockGalleryApiService galleryApiService = getMockService(null);
        RequestBody requestBody = mock(RequestBody.class);
        galleryApiService.uploadPhoto(requestBody).blockingFirst();
    }
}
