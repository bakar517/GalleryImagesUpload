package com.coding.androidgallery.util;

/**
 * Created by Mudassar Hussain on 5/27/2019.
 */
public class AppHelper {

    public static int getHashCode(Object arg) {
        int result = 0;
        if(arg == null) return result;
        if(arg instanceof Integer){
            result = ((Integer)arg).hashCode();
        }else if(arg instanceof Long){
            result = ((Long)arg).hashCode();
        }else if(arg instanceof Double){
            result = ((Double)arg).hashCode();
        }else if(arg instanceof Boolean){
            result = ((Boolean)arg).hashCode();
        }else if(arg instanceof String){
            result = ((String)arg).hashCode();
        }else{
            result  = arg.hashCode();
        }
        return result;
    }
}