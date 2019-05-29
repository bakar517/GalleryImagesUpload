package com.coding.androidgallery.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mudassar Hussain on 5/26/2019.
 */
public class GalleryResponse extends BaseResponse {
    @Expose
    private boolean hasMore;

    @SerializedName("list")
    @Expose
    private List<GalleryImage> galleryList = new ArrayList<>();

    public List<GalleryImage> getGalleryList() {
        return galleryList;
    }

    public void setGalleryList(List<GalleryImage> galleryList) {
        this.galleryList = galleryList;
    }

    public boolean isHasMore() {
        return hasMore;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }
}
