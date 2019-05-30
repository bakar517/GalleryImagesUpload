package com.coding.androidgallery.util;

import android.widget.ImageView;

/**
 * Created by Mudassar Hussain on 6/1/2019.
 * PhotoLoader interface is used to provide abstraction to load Image.
 * We can provide any kind of impaction e.g. retrofit, Glide or Picasso etc
 */
public interface PhotoLoader {
    void loadPhoto(String url, ImageView targetView);
}
