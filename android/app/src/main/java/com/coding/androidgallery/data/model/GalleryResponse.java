package com.coding.androidgallery.data.model;

import com.coding.androidgallery.util.AppHelper;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mudassar Hussain on 5/26/2019.
 */
public class GalleryResponse extends BaseResponse {

    public GalleryResponse(){

    }

    public GalleryResponse(int statusCode,String message){
        super(statusCode,message);
    }

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

    public boolean hasMore() {
        return hasMore;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if (this == obj) return true;

        if (!super.equals(obj)){
            return false;
        }

        GalleryResponse other = (GalleryResponse)obj;
        if(hasMore != other.hasMore){
            return false;
        }
        if(galleryList != null && !galleryList.equals(other.galleryList)){
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int prime = 41;
        int result = super.hashCode();
        result = prime * result + AppHelper.getHashCode(hasMore);
        result = prime * result + AppHelper.getHashCode(galleryList);
        return result;
    }

}
