package com.coding.androidgallery.ui.main.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.coding.androidgallery.R;
import com.coding.androidgallery.data.model.GalleryImage;
import com.coding.androidgallery.util.PhotoLoader;

/**
 * Created by Mudassar Hussain on 6/1/2019.
 */
public class GalleryItemViewHolder extends RecyclerView.ViewHolder {
    ImageView photo;

    public GalleryItemViewHolder(@NonNull View itemView) {
        super(itemView);

        photo = itemView.findViewById(R.id.photo);
    }

    public void bind(GalleryImage item, PhotoLoader photoLoader){
        if(item == null)    return;
        photoLoader.loadPhoto(item.getPublicPath(),photo);
    }
}