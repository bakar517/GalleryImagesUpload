package com.coding.androidgallery.data.model;

import com.coding.androidgallery.util.AppHelper;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mudassar Hussain on 5/26/2019.
 */
public class UploadResponse extends BaseResponse{

    public UploadResponse(){

    }

    public UploadResponse(int statusCode,String message){
        super(statusCode,message);
    }

    @Expose
    private GalleryImage item;

    public GalleryImage getItem() {
        return item;
    }

    public void setItem(GalleryImage item) {
        this.item = item;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if (this == obj) return true;

        if (!super.equals(obj)){
            return false;
        }

        UploadResponse other = (UploadResponse)obj;
        if(item != null && !item.equals(other.getItem())){
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int prime = 41;
        int result = super.hashCode();
        if(item != null) {
            result = prime * result + item.hashCode();
        }
        return result;
    }
}
