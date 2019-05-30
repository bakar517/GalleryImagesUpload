package com.coding.androidgallery.data.model;

import com.coding.androidgallery.util.AppHelper;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Mudassar Hussain on 5/26/2019.
 */
public class GalleryImage {

    public GalleryImage(){

    }

    public GalleryImage(long id,String publicPath,String  createdDate,String deviceInfo){
        this.id = id;
        this.publicPath = publicPath;
        this.createdDate = createdDate;
        this.deviceInfo = deviceInfo;
    }


    @Expose
    private long id;

    @SerializedName("public_path")
    @Expose
    private String publicPath;

    @SerializedName("uploaded_date")
    @Expose
    private String  createdDate;

    @SerializedName("device_info")
    @Expose
    private String deviceInfo;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPublicPath() {
        return publicPath;
    }

    public void setPublicPath(String publicPath) {
        this.publicPath = publicPath;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if (this == obj) return true;

        GalleryImage other = (GalleryImage)obj;
        if(id != other.id){
            return false;
        }
        if(publicPath != null && !publicPath.equals(other.publicPath)){
            return false;
        }
        if(createdDate != null && !createdDate.equals(other.createdDate)){
            return false;
        }
        if(deviceInfo!= null && !deviceInfo.equals(other.deviceInfo)){
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int prime = 41;
        int result = AppHelper.getHashCode(id);
        result = prime * result + AppHelper.getHashCode(publicPath);
        result = prime * result + AppHelper.getHashCode(createdDate);
        result = prime * result + AppHelper.getHashCode(deviceInfo);
        return result;
    }
}
