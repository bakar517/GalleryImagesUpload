package com.coding.androidgallery.util.android;

import android.support.test.runner.AndroidJUnit4;

import com.coding.androidgallery.data.model.DeviceInfo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by Mudassar Hussain on 5/30/2019.
 */
@RunWith(AndroidJUnit4.class)
public class AndroidHelperTest {

    @Test
    public void deviceInfo_ShouldReturnValidJson(){
        DeviceInfo deviceInfo = AndroidHelper.getDeviceInfo();
        Assert.assertNotNull(deviceInfo.getDeviceModel());
        Assert.assertTrue(deviceInfo.toJson().size() != 0);
    }
}
