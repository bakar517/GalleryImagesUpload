package com.coding.androidgallery;

/**
 * Created by Mudassar Hussain on 6/3/2019.
 */
public interface AppConfig {
    String BASE_URL_LOCAL =  "http://192.168.1.207/GalleryImagesUpload/backend/api/";

    String BASE_URL_PRODUCTION =  "http://apptestbox.com/GalleryImagesUpload/backend/api/";

    String ENV_LOCAL = "local_env";

    String ENV_PRODUCTION = "production_env";

    String CURRENT_ENV = ENV_PRODUCTION;

    String BASE_URL = CURRENT_ENV == ENV_PRODUCTION ? BASE_URL_PRODUCTION : BASE_URL_LOCAL;

    String CURRENT_LOGGED_IN_USER_ID = "11";
}
