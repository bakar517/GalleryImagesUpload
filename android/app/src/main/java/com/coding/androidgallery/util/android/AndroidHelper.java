package com.coding.androidgallery.util.android;

import android.app.Activity;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;

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

    public static int columnCount(Activity activity,float width){
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int columns = Math.round(size.x/width);
        return columns;
    }
}
