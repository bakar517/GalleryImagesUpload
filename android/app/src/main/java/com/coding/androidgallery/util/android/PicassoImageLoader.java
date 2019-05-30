package com.coding.androidgallery.util.android;

import android.content.Context;
import android.widget.ImageView;

import com.coding.androidgallery.data.remote.api.NetworkClientBuilder;
import com.coding.androidgallery.util.PhotoLoader;
import com.squareup.picasso.Picasso;

/**
 * Created by Mudassar Hussain on 6/1/2019.
 * PicassoImageLoader provide implementation of PhotoLoader for Picasso.
 * It contains different kind of configurations Like cache etc
 */
public class PicassoImageLoader implements PhotoLoader {
    final long CACHE_SIZE = 50*1024*1024;

    Picasso instance;
    public PicassoImageLoader(Context context){
        instance = new  Picasso.Builder(context).downloader(
                new NetworkClientBuilder.HttpClientBuilder(context).cache(CACHE_SIZE).build()).build();
    }
    @Override
    public void loadPhoto(String url, ImageView targetView) {
        instance.load(url).fit().centerCrop().into(targetView);
    }
}
