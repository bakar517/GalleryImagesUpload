package com.coding.androidgallery.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Mudassar Hussain on 5/26/2019.
 */
public class GalleryImage {
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
}
