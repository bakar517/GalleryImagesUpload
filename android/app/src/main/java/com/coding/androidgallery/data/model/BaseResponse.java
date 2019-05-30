package com.coding.androidgallery.data.model;

import com.coding.androidgallery.util.AppHelper;
import com.google.gson.annotations.Expose;

/**
 * Created by Mudassar Hussain on 5/26/2019.
 */
public class BaseResponse {

    public static final int SUCCESS_CODE = 1;

    public static final int ERROR_CODE = -1;

    public BaseResponse(){

    }

    public BaseResponse(int statusCode,String message){
        setStatusCode(statusCode);
        setMessage(message);
    }

    @Expose
    private int statusCode;

    @Expose
    private String message;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean hasError(){
        return statusCode <= ERROR_CODE;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if (this == obj) return true;

        if (this.getClass() != obj.getClass()){
            return false;
        }

        BaseResponse other = (BaseResponse)obj;

        if(getStatusCode() != other.getStatusCode()){
            return false;
        }
        if(getMessage() != null && !getMessage().equals(other.getMessage())){
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int prime = 41;
        int result = AppHelper.getHashCode(getStatusCode());
        result = prime * result + AppHelper.getHashCode(getMessage());
        return result;
    }
}
