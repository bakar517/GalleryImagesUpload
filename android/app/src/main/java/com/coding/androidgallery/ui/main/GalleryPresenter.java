package com.coding.androidgallery.ui.main;

import com.coding.androidgallery.data.GalleryDataManager;
import com.coding.androidgallery.data.model.GalleryImage;
import com.coding.androidgallery.data.model.GalleryResponse;
import com.coding.androidgallery.data.model.UploadResponse;
import com.coding.androidgallery.data.model.User;
import com.coding.androidgallery.ui.base.BasePresenter;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

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
    public void
    uploadPhoto(String imageFilePath) {
         Disposable disposable = galleryDataManager.uploadPhoto(imageFilePath)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<UploadResponse>() {
                    @Override
                    public void accept(UploadResponse uploadResponse) throws Exception {
                        if(!uploadResponse.hasError()){
                            if(getView() != null){
                                getView().photoUploaded(uploadResponse.getItem());
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

    @Override
    public void fetchPhotos(long lastSeen) {
        Disposable disposable = galleryDataManager.fetchAll(lastSeen)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<GalleryResponse>() {
                    @Override
                    public void accept(GalleryResponse galleryResponse) throws Exception {
                        if(!galleryResponse.hasError()){
                            if(getView() != null){
                                getView().onNewMedia(galleryResponse.getGalleryList());
                            }
                        }else{
                            if(getView() != null){
                                getView().errorFetchingPhotos();
                            }
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if(getView() != null){
                            getView().errorFetchingPhotos();
                        }
                    }
                });

        addToDisposable(disposable);
    }
}
