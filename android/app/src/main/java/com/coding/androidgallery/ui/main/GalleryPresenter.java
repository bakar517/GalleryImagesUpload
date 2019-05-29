package com.coding.androidgallery.ui.main;

import com.coding.androidgallery.data.GalleryDataManager;
import com.coding.androidgallery.data.model.ImageUploadResponse;
import com.coding.androidgallery.data.model.User;
import com.coding.androidgallery.ui.base.BasePresenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by Mudassar Hussain on 5/26/2019.
 */
public class GalleryPresenter extends BasePresenter<GalleryContract.GalleryView> implements GalleryContract.Presenter {
    private GalleryDataManager galleryDataManager;

    public GalleryPresenter(GalleryContract.GalleryView view,GalleryDataManager dataManager){
        super(view);
        this.galleryDataManager = dataManager;
    }

    @Override
    public void uploadPhoto(String imageFilePath) {
         Disposable disposable = galleryDataManager.uploadPhoto(imageFilePath)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ImageUploadResponse>() {
                    @Override
                    public void accept(ImageUploadResponse uploadResponse) throws Exception {
                        if(!uploadResponse.hasError()){
                            if(getView() != null){
                                getView().photoUploaded();
                            }
                        }else{
                            if(getView() != null){
                                getView().errorUploadingPhoto();
                            }
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if(getView() != null){
                            getView().errorUploadingPhoto();
                        }
                    }
                });

         addToDisposable(disposable);
    }

    @Override
    public User getUserInfo() {
        return galleryDataManager.getUserInfo();
    }
}
