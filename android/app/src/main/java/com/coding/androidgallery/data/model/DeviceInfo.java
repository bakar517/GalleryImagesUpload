package com.coding.androidgallery.data.model;

import com.coding.androidgallery.util.AppHelper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.annotations.Expose;

/**
 * Created by Mudassar Hussain on 5/26/2019.
 */
public class DeviceInfo {

    @Expose
    private String deviceModel;

    public DeviceInfo(){

    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if (this == obj) return true;

        if (this.getClass() != obj.getClass()){
            return false;
        }

        DeviceInfo other = (DeviceInfo)obj;

        if(getDeviceModel() != null && !getDeviceModel().equals(other.getDeviceModel())){
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = AppHelper.getHashCode(getDeviceModel());

        return result;
    }

    public JsonObject toJson(){
        return  (new JsonParser()).parse(this.toString()).getAsJsonObject();
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
