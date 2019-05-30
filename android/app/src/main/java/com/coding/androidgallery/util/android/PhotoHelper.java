package com.coding.androidgallery.util.android;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

/**
 * Created by Mudassar Hussain on 5/27/2019.
 */
public class PhotoHelper {
    public static String queryPhoto(Context context, Uri selectedImageUri){
        String path = "";
        Cursor cursor = context.getContentResolver().query(selectedImageUri,
                new String[]{MediaStore.Images.Media.DATA}, null, null, null);
        if(cursor == null) return path;
        if(cursor.moveToFirst()) {
            path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        }
        cursor.close();
        return path;
    }
}
