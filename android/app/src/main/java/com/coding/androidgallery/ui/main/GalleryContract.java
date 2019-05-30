package com.coding.androidgallery.ui.main;

import com.coding.androidgallery.data.model.GalleryImage;
import com.coding.androidgallery.data.model.User;

import java.util.List;

/**
 * Created by Mudassar Hussain on 5/26/2019.
 * This contract is bridge between View and Presenter
 */
public class GalleryContract {

    /**
     * GalleryView represent View in Presenter
     */

    interface GalleryView{
        void photoUploaded(GalleryImage image);
        void errorUploadingPhoto();
        void onNewMedia(List<GalleryImage> list);
        void errorFetchingPhotos();
    }

    /**
     * Presenter represent presenter in View
     */

    interface Presenter{
        void uploadPhoto(String imageFilePath);
        User getUserInfo();
        void fetchPhotos(long lastSeen);
    }
}
