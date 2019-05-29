package com.coding.androidgallery.data.model;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Mudassar Hussain on 5/27/2019.
 */
public class UploadResponseTest {

    @Test
    public void testEqualsContract_CompareEqual_ShouldBeSymmetric() {
        UploadResponse obj1 = new UploadResponse();
        obj1.setStatusCode(BaseResponse.SUCCESS_CODE);
        Assert.assertTrue(obj1.equals(obj1));
        UploadResponse obj2 = new UploadResponse();
        obj2.setStatusCode(BaseResponse.SUCCESS_CODE);
        Assert.assertTrue(obj2.equals(obj2));
        Assert.assertTrue(obj1.equals(obj2) && obj2.equals(obj1));
        Assert.assertTrue(obj1.hashCode() == obj2.hashCode());
    }

    @Test
    public void testEqualsContract_CompareEqual_ShouldNotEqual() {
        UploadResponse obj1 = new UploadResponse();
        obj1.setStatusCode(BaseResponse.SUCCESS_CODE);
        Assert.assertFalse(obj1.equals(null));
        User obj2 = new User();
        Assert.assertFalse(obj2.equals(null));
        Assert.assertFalse(obj1.equals(obj2));
        Assert.assertFalse(obj2.equals(obj1));
    }

    @Test
    public void testSuccessResponse_ShouldNotShowError() {
        UploadResponse response = new UploadResponse();
        response.setStatusCode(BaseResponse.SUCCESS_CODE);
        Assert.assertFalse(response.hasError());
    }

    @Test
    public void testSuccessResponse_ShouldShowError() {
        UploadResponse response = new UploadResponse();
        response.setStatusCode(BaseResponse.ERROR_CODE);
        Assert.assertTrue(response.hasError());
    }
}
