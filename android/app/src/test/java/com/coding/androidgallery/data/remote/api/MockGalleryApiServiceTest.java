package com.coding.androidgallery.data.remote.api;

import com.coding.androidgallery.data.model.ImageUploadResponse;

import org.junit.Assert;
import org.junit.Test;

import okhttp3.RequestBody;

import static org.mockito.Mockito.mock;

/**
 * Created by Mudassar Hussain on 5/28/2019.
 */
public class MockGalleryApiServiceTest {

    @Test
    public void testSuccessfullyUploaded() {
        ImageUploadResponse mockResponse = mock(ImageUploadResponse.class);
        MockGalleryApiService galleryApiService = new MockGalleryApiService(mockResponse);
        RequestBody requestBody = mock(RequestBody.class);
        ImageUploadResponse response = galleryApiService.uploadPhoto(requestBody).blockingFirst();
        Assert.assertNotNull(response);
        Assert.assertEquals(mockResponse,response);
    }

    @Test(expected= Exception.class)
    public void testErrorInUploading() {
        MockGalleryApiService galleryApiService = new MockGalleryApiService();
        RequestBody requestBody = mock(RequestBody.class);
        galleryApiService.uploadPhoto(requestBody).blockingFirst();
    }
}
