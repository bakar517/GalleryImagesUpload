package com.coding.androidgallery.util;

/**
 * Created by Mudassar Hussain on 5/27/2019.
 */
public class AppHelper {

    public static int getHashCode(Object arg) {
        int result = 0;
        if(arg == null) return result;
        if(arg instanceof Integer){
            result = new Integer((int)arg).hashCode();
        }else if(arg instanceof Long){
            result = new Long((long)arg).hashCode();
        }else if(arg instanceof Double){
            result = new Double((double)arg).hashCode();
        }else{
            result  = arg.hashCode();
        }
        return result;
    }
}
