package com.coding.androidgallery.data.model;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Mudassar Hussain on 5/27/2019.
 */
public class ImageUploadResponseTest {

    @Test
    public void testEqualsContract_CompareEqual_ShouldBeSymmetric() {
        ImageUploadResponse obj1 = new ImageUploadResponse();
        obj1.setStatusCode(BaseResponse.SUCCESS_CODE);
        Assert.assertTrue(obj1.equals(obj1));
        ImageUploadResponse obj2 = new ImageUploadResponse();
        obj2.setStatusCode(BaseResponse.SUCCESS_CODE);
        Assert.assertTrue(obj2.equals(obj2));
        Assert.assertTrue(obj1.equals(obj2) && obj2.equals(obj1));
        Assert.assertTrue(obj1.hashCode() == obj2.hashCode());
    }

    @Test
    public void testEqualsContract_CompareEqual_ShouldNotEqual() {
        ImageUploadResponse obj1 = new ImageUploadResponse();
        obj1.setStatusCode(BaseResponse.SUCCESS_CODE);
        Assert.assertFalse(obj1.equals(null));
        User obj2 = new User();
        Assert.assertFalse(obj2.equals(null));
        Assert.assertFalse(obj1.equals(obj2));
        Assert.assertFalse(obj2.equals(obj1));
    }

    @Test
    public void testSuccessResponse_ShouldNotShowError() {
        ImageUploadResponse response = new ImageUploadResponse();
        response.setStatusCode(BaseResponse.SUCCESS_CODE);
        Assert.assertFalse(response.hasError());
    }

    @Test
    public void testSuccessResponse_ShouldShowError() {
        ImageUploadResponse response = new ImageUploadResponse();
        response.setStatusCode(BaseResponse.ERROR_CODE);
        Assert.assertTrue(response.hasError());
    }
}
