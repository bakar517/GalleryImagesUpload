package com.coding.androidgallery.ui.main;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.View;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowApplication;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.functions.Function;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.Schedulers;

import static android.app.Activity.RESULT_OK;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.robolectric.Shadows.shadowOf;

/**
 * Created by Mudassar Hussain on 5/27/2019.
 */
@RunWith(RobolectricTestRunner.class)
@Config(manifest = "AndroidManifest.xml")
public class GalleryActivityUploadTest {
    GalleryActivity activity;

    @Before
    public void init(){
        activity = Robolectric.buildActivity( GalleryActivity.class ).create().resume().get();
    }

    @Test
    public void testGalleryActivity_NotNull(){
        Assert.assertNotNull(activity);
    }

    @Test
    public void testGalleryActivity_Views_ShouldVisibleAndClickable(){
        //visibility
        Assert.assertTrue(activity.camera.getVisibility() == View.VISIBLE);
        Assert.assertTrue(activity.pick_from_gallery.getVisibility() == View.VISIBLE);

        //Clickable
        Assert.assertTrue(activity.camera.isEnabled());
        Assert.assertTrue(activity.pick_from_gallery.isEnabled());
    }

    @Test
    public void test_PermissionRequired(){
        Assert.assertFalse(activity.hasStoragePermission(activity.getApplicationContext()));
        ShadowApplication app = shadowOf(activity.getApplication());
        app.grantPermissions(Manifest.permission.READ_EXTERNAL_STORAGE);
        Assert.assertTrue(activity.hasStoragePermission(activity.getApplicationContext()));
    }

    @Test
    public void test_BitmapStore_ShouldSave(){
        Bitmap testBitmap = Bitmap.createBitmap(30, 30, Bitmap.Config.ARGB_8888);
        TestObserver<String> observer = activity.storeBitmap(testBitmap).test();
        observer.awaitTerminalEvent(2, TimeUnit.SECONDS);
        observer.assertNoErrors();
        observer.assertValueCount(1);
    }

    @Test
    public void test_BitmapStore_ShouldThrowError(){
        Bitmap testBitmap = null;
        TestObserver<String> observer = activity.storeBitmap(testBitmap).test();
        observer.awaitTerminalEvent(2, TimeUnit.SECONDS);
        observer.assertError(Exception.class);
        observer.assertValueCount(0);
    }

    @Test
    public void test_UploadBitmap_UploadSuccessfully(){
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(new Function<Callable<Scheduler>, Scheduler>() {
            @Override
            public Scheduler apply(Callable<Scheduler> schedulerCallable) throws Exception {
                return Schedulers.trampoline();
            }
        });
        Bitmap testBitmap = Bitmap.createBitmap(30, 30, Bitmap.Config.ARGB_8888);
        TestObserver<String> observer = activity.uploadBitmap(testBitmap).test();
        Assert.assertTrue(activity.isUploading());
        observer.awaitTerminalEvent(2, TimeUnit.SECONDS);
        observer.assertNoErrors();
        observer.assertValueCount(1);
    }

    @Test
    public void test_TakePicture_DoesDeviceHasCamera(){
        boolean hasCamera = activity.hasCamera();
        Assert.assertEquals(hasCamera,activity.takePicture());
    }

    @Test
    public void test_MockPictureCapture_ShouldStartUploading(){
        //assume device has camera so launch intent for camera and mock onActivityResult behavior
        activity.takePicture();
        Bitmap mockBitmap = Bitmap.createBitmap(20, 20, Bitmap.Config.ARGB_8888);
        Intent data = new Intent();
        data.putExtra("data",mockBitmap);
        activity.handleActivityResult(GalleryActivity.REQUEST_CODE_TAKE_PICTURE,RESULT_OK,data);
        Assert.assertTrue(activity.isUploading());
    }

    @Test
    public void testUploadImage_WithNullPath_ShouldShowError(){
        //launch intent to pick from gallery
        GalleryActivity spy = Mockito.spy(activity);
        String path = null;
        spy.upload(path);
        verify(spy).showMessage(anyString());
    }

    @Test
    public void testUploadImage_WithNullBitmap_ShouldShowError(){
        //launch intent to pick from gallery
        GalleryActivity spy = Mockito.spy(activity);
        Bitmap bitmap = null;
        spy.upload(bitmap);
        verify(spy).showMessage(anyString());
    }
}
