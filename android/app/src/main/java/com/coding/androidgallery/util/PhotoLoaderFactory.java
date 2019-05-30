package com.coding.androidgallery.util;

import android.content.Context;

import com.coding.androidgallery.util.android.PicassoImageLoader;

/**
 * Created by Mudassar Hussain on 6/1/2019.
 * PhotoLoaderFactory is Factory class that provide different kind of PhotoLoader application provide.
 */
public class PhotoLoaderFactory {

    public static PhotoLoader picasso(Context context){
        return new PicassoImageLoader(context);
    }
}
