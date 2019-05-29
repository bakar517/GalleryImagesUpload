package com.coding.androidgallery.util.android;

import android.os.Build;

import com.coding.androidgallery.data.model.DeviceInfo;

/**
 * Created by Mudassar Hussain on 5/26/2019.
 */
public class AndroidHelper {
    public static DeviceInfo getDeviceInfo(){
        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.setDeviceModel(Build.MODEL);
        return deviceInfo;
    }
}
