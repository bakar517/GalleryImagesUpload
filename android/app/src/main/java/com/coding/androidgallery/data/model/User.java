package com.coding.androidgallery.data.model;

/**
 * Created by Mudassar Hussain on 5/27/2019.
 */
public class User {
    private String userId;

    public User(){

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public static User createUser(String id){
        User user = new User();
        user.setUserId(id);
        return user;
    }
}
