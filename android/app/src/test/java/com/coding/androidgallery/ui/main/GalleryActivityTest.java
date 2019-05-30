package com.coding.androidgallery.ui.main;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;

import com.coding.androidgallery.data.GalleryDataManager;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowApplication;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import javax.annotation.meta.When;

import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.functions.Function;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.Schedulers;

import static android.app.Activity.RESULT_OK;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.robolectric.Shadows.shadowOf;

/**
 * Created by Mudassar Hussain on 5/27/2019.
 */
@RunWith(RobolectricTestRunner.class)
@Config(manifest = "AndroidManifest.xml")
public class GalleryActivityTest {

    @Test
    public void testGalleryActivity_UploadPhoto_ShouldShowUploading(){
        GalleryActivity activity = Robolectric.buildActivity( GalleryActivity.class ).create().resume().get();

        Assert.assertNotNull(activity.presenter);

        GalleryPresenter galleryPresenter = Mockito.spy(activity.presenter);

        doNothing().when(galleryPresenter).uploadPhoto(anyString());

        activity.upload("test.png");

        Assert.assertTrue(activity.isUploading());
    }

}
