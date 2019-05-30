package com.coding.androidgallery.ui.main;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.coding.androidgallery.App;
import com.coding.androidgallery.R;
import com.coding.androidgallery.data.GalleryDataManager;
import com.coding.androidgallery.data.model.GalleryImage;
import com.coding.androidgallery.ui.base.BaseActivity;
import com.coding.androidgallery.ui.main.adapter.GalleryPhotoAdapter;
import com.coding.androidgallery.util.PhotoLoader;
import com.coding.androidgallery.util.android.AndroidHelper;
import com.coding.androidgallery.util.android.FileHelper;
import com.coding.androidgallery.util.android.PhotoHelper;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class GalleryActivity extends BaseActivity<GalleryPresenter> implements GalleryContract.GalleryView, View.OnClickListener {
    public static final int REQUEST_CODE_STORAGE_PERMISSION = 11;

    public static final int REQUEST_CODE_PICK_IMAGE = 12;

    public static final int REQUEST_CODE_TAKE_PICTURE = 13;

    RecyclerView photoList;
    GalleryPhotoAdapter galleryAdapter;
    ImageButton camera,pick_from_gallery;

    boolean hasStoragePermission,stateUploading;

    long lastSeen ;

    @Inject
    GalleryDataManager dataManager;

    @Inject
    PhotoLoader photoLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        App.getInstance().appComponent.inject(this);

        initViews();

        presenter = new GalleryPresenter(this,dataManager);

        if(!hasStoragePermission(getApplicationContext())){
            requestStoragePermission();
        }

        presenter.fetchPhotos(lastSeen);
    }

    private void initViews() {
        camera = findViewById(R.id.ic_camera);
        camera.setOnClickListener(this);
        pick_from_gallery = findViewById(R.id.ic_pick_from_gallery);
        pick_from_gallery.setOnClickListener(this);

        photoList = findViewById(R.id.photoList);
        galleryAdapter = new GalleryPhotoAdapter(getApplicationContext(), photoLoader);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),AndroidHelper.columnCount(this,getResources().getDimension(R.dimen.photo_list_item_size)));
        photoList.setLayoutManager(layoutManager);
        photoList.setAdapter(galleryAdapter);
    }

    public boolean hasStoragePermission(Context context) {
        if (Build.VERSION.SDK_INT >= 23) {
            if(ContextCompat.checkSelfPermission(context,Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                hasStoragePermission = true;
            }else{
                hasStoragePermission = false;
            }
        }else{
            hasStoragePermission = true;
        }
        return hasStoragePermission;
    }

    public void requestStoragePermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                REQUEST_CODE_STORAGE_PERMISSION);
    }

    @Override
    public void photoUploaded(GalleryImage image) {
        setStateUploading(false);
        showMessage("Photo uploaded");
        galleryAdapter.add(image);
    }

    @Override
    public void errorUploadingPhoto() {
        setStateUploading(false);
        showMessage(R.string.message_error_upload);
    }

    @Override
    public void onNewMedia(List<GalleryImage> list) {
        galleryAdapter.add(list);
    }

    @Override
    public void errorFetchingPhotos() {
        showMessage(R.string.message_error_fetching_new_images);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case REQUEST_CODE_STORAGE_PERMISSION:
                if(grantResults.length > 0){
                    hasStoragePermission = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        handleActivityResult(requestCode,resultCode,data);
    }

    public void handleActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        switch (requestCode){
            case REQUEST_CODE_PICK_IMAGE:
                if(resultCode == RESULT_OK){
                    Uri uri = data.getData();
                    String path = PhotoHelper.queryPhoto(getApplicationContext(),uri);
                    upload(path);
                }
                break;
            case REQUEST_CODE_TAKE_PICTURE:
                Bundle extras = data.getExtras();
                Bitmap takenPhoto = (Bitmap) extras.get("data");
                upload(takenPhoto);
                break;
        }
    }

    public void upload(String path){
        if(!TextUtils.isEmpty(path)){
            setStateUploading(true);
            presenter.uploadPhoto(path);
        }else{
            showMessage(R.string.message_error);
        }
    }

    public void upload(Bitmap bitmap){
        if(bitmap != null){
            Disposable disposable = uploadBitmap(bitmap)
                    .subscribe(new Consumer<String>() {
                        @Override
                        public void accept(String path) throws Exception {
                            upload(path);
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            setStateUploading(false);
                            showMessage(R.string.message_error);
                        }
                    });

            presenter.addToDisposable(disposable);

        }else{
            showMessage(R.string.message_error);
        }
    }

    public Observable<String> storeBitmap(final Bitmap bitmap){
        final Context context = getApplicationContext();
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                try {
                    String path = FileHelper.saveBitmapToFile(context,bitmap);
                    e.onNext(path);
                    e.onComplete();
                }catch (Exception ex){
                    e.onError(ex);
                }
            }
        }).subscribeOn(Schedulers.io());
    }

    public Observable<String> uploadBitmap(Bitmap bitmap){
        setStateUploading(true);
        return storeBitmap(bitmap)
                .observeOn(AndroidSchedulers.mainThread());
    }

    public void showMessage(int resId) {
        showMessage(getString(resId));
    }

    public void showMessage(String message) {
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.ic_camera){
            takePicture();
        }else if(v.getId() == R.id.ic_pick_from_gallery){
            pickFromGallery();
        }
    }

    public boolean hasCamera(){
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA);
    }

    public boolean takePicture() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (hasCamera() && takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_CODE_TAKE_PICTURE);
            return true;
        }else{
            showMessage( R.string.message_no_camera);
            return false;
        }
    }

    public void pickFromGallery() {
        if(hasStoragePermission){
            Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
            photoPickerIntent.setType("image/*");
            startActivityForResult(photoPickerIntent, REQUEST_CODE_PICK_IMAGE);
        }else{
            showMessage(R.string.message_storage_permission);
        }
    }

    void setStateUploading(boolean value){
        stateUploading = value;
    }

    public boolean isUploading(){
        return stateUploading;
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        GridLayoutManager gridLayoutManager = (GridLayoutManager) photoList.getLayoutManager();
        int scrollPosition = gridLayoutManager.findFirstCompletelyVisibleItemPosition();
        gridLayoutManager.setSpanCount(AndroidHelper.columnCount(this,getResources().getDimension(R.dimen.photo_list_item_size)));
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            photoList.setLayoutManager(gridLayoutManager);
            photoList.scrollToPosition(scrollPosition);
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            photoList.setLayoutManager(gridLayoutManager);
            photoList.scrollToPosition(scrollPosition);
        }
    }
}
