package com.coding.androidgallery.ui.main.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.coding.androidgallery.R;
import com.coding.androidgallery.data.model.GalleryImage;
import com.coding.androidgallery.util.PhotoLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mudassar Hussain on 6/1/2019.
 */
public class GalleryPhotoAdapter extends RecyclerView.Adapter<GalleryItemViewHolder> {

    LayoutInflater layoutInflater;
    List<GalleryImage> data;
    PhotoLoader photoLoader;

    public GalleryPhotoAdapter(Context context,PhotoLoader photoLoader){
        this.photoLoader = photoLoader;
        layoutInflater = LayoutInflater.from(context);
        data = new ArrayList<>();
    }

    public void add(List<GalleryImage> list){
        int positionStart = getItemCount();
        int itemCount = list.size();
        data.addAll(list);
        notifyItemRangeInserted(positionStart, itemCount);
    }

    public void add(GalleryImage item){
        int positionStart = getItemCount();
        data.add(item);
        notifyItemRangeInserted(positionStart, 1);
    }

    public GalleryImage getItem(int position) {
        return data.get(position);
    }

    public List<GalleryImage> getItems(){
        return data;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @NonNull
    @Override
    public GalleryItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.photo_list_item,viewGroup,false);
        return new GalleryItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryItemViewHolder viewHolder, int i) {
        GalleryImage item = getItem(i);
        viewHolder.bind(item,photoLoader);
    }

}
