package com.coding.androidgallery.util.android;

import android.content.Context;
import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Mudassar Hussain on 5/27/2019.
 */
public class FileHelper {
    static final String FILE_NAME = "camera-captured.png";

    public static String saveBitmapToFile(Context context, Bitmap bitmap) throws IOException {
        File file = new File(context.getCacheDir(), FILE_NAME);
        if(file.exists()){
            file.delete();
        }
        file.createNewFile();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100 , byteArrayOutputStream);
        byte[] data = byteArrayOutputStream.toByteArray();
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(data);
        fileOutputStream.flush();
        fileOutputStream.close();
        return file.getAbsolutePath();
    }
}