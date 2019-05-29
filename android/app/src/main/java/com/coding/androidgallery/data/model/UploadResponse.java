package com.coding.androidgallery.data.model;

import com.coding.androidgallery.util.AppHelper;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Mudassar Hussain on 5/26/2019.
 */
public class UploadResponse extends BaseResponse{
    @SerializedName("public_path")
    @Expose
    private String publicPath;


    public String getPublicPath() {
        return publicPath;
    }

    public void setPublicPath(String publicPath) {
        this.publicPath = publicPath;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if (this == obj) return true;

        if (!super.equals(obj)){
            return false;
        }

        UploadResponse other = (UploadResponse)obj;
        if(getPublicPath() != null && !getPublicPath().equals(other.getPublicPath())){
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int prime = 41;
        int result = super.hashCode();
        result = prime * result + AppHelper.getHashCode(getPublicPath());
        return result;
    }
}
