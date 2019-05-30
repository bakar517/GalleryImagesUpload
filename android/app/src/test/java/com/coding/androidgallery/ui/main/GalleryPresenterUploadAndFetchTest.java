package com.coding.androidgallery.ui.main;

import com.coding.androidgallery.DaggerTestAppComponent;
import com.coding.androidgallery.TestAppComponent;
import com.coding.androidgallery.data.GalleryDataManager;
import com.coding.androidgallery.data.model.GalleryImage;
import com.coding.androidgallery.data.model.GalleryResponse;
import com.coding.androidgallery.data.model.MockResponseFactory;
import com.coding.androidgallery.data.model.UploadResponse;
import com.coding.androidgallery.modules.MockNetworkModule;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Mudassar Hussain on 5/30/2019.
 */
public class GalleryPresenterUploadAndFetchTest {
    @Inject
    GalleryDataManager dataManager;

    @Before
    public void setup(){
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(new Function<Callable<Scheduler>, Scheduler>() {
            @Override
            public Scheduler apply(Callable<Scheduler> schedulerCallable) throws Exception {
                return Schedulers.trampoline();
            }
        });
    }

    private void setupMockResponseWithDI(UploadResponse mock){
        TestAppComponent appComponent = DaggerTestAppComponent.builder().mockNetworkModule(new MockNetworkModule(mock)).build();
        appComponent.inject(this);
    }

    private void setupMockResponseWithDI(GalleryResponse mock){
        TestAppComponent appComponent = DaggerTestAppComponent.builder().mockNetworkModule(new MockNetworkModule(mock)).build();
        appComponent.inject(this);
    }

    @Test
    public void uploadPhoto_WithFilePath_ShouldShowUploadedPhoto() throws InterruptedException {
        String imageFilePath = "test.png";
        final boolean[] status = new boolean[1];
        UploadResponse response = MockResponseFactory.mockUploadResponse();
        setupMockResponseWithDI(response);
        final CountDownLatch lock = new CountDownLatch(1);
        GalleryPresenter presenter = new GalleryPresenter(new GalleryContract.GalleryView() {
            @Override
            public void photoUploaded(GalleryImage galleryImage) {
                status[0] = true;
                lock.countDown();
            }

            @Override
            public void errorUploadingPhoto() {

            }

            @Override
            public void onNewMedia(List<GalleryImage> list) {

            }

            @Override
            public void errorFetchingPhotos() {

            }
        }, dataManager);

        presenter.uploadPhoto(imageFilePath);
        lock.await(2, TimeUnit.SECONDS);
        Assert.assertTrue(status[0]);
    }

    @Test
    public void uploadPhoto_WithFilePath_ShouldShowError() throws InterruptedException {
        String imageFilePath = "test.png";
        UploadResponse response = null;
        setupMockResponseWithDI(response);
        final boolean[] error = new boolean[1];
        final CountDownLatch lock = new CountDownLatch(1);
        GalleryPresenter presenter = new GalleryPresenter(new GalleryContract.GalleryView() {
            @Override
            public void photoUploaded(GalleryImage galleryImage) {

            }

            @Override
            public void errorUploadingPhoto() {
                error[0] = true;
                lock.countDown();
            }

            @Override
            public void onNewMedia(List<GalleryImage> list) {

            }

            @Override
            public void errorFetchingPhotos() {

            }
        }, dataManager);

        presenter.uploadPhoto(imageFilePath);
        lock.await(2, TimeUnit.SECONDS);
        Assert.assertTrue(error[0]);
    }


    @Test
    public void fetchPhotos_ShouldShowNewMedia() throws InterruptedException {
        long lastSeen = 0;
        final boolean[] status = new boolean[1];
        GalleryResponse response = MockResponseFactory.mockGalleryResponse();
        setupMockResponseWithDI(response);
        final CountDownLatch lock = new CountDownLatch(1);
        GalleryPresenter presenter = new GalleryPresenter(new GalleryContract.GalleryView() {
            @Override
            public void photoUploaded(GalleryImage galleryImage) {

            }

            @Override
            public void errorUploadingPhoto() {

            }

            @Override
            public void onNewMedia(List<GalleryImage> list) {
                status[0] = true;
                lock.countDown();
            }

            @Override
            public void errorFetchingPhotos() {

            }
        }, dataManager);

        presenter.fetchPhotos(lastSeen);
        lock.await(2, TimeUnit.SECONDS);
        Assert.assertTrue(status[0]);
    }

    @Test
    public void fetchPhotos_WithFilePath_ShouldShowError() throws InterruptedException {
        long lastSeen = 0;
        GalleryResponse response = null;
        setupMockResponseWithDI(response);
        final boolean[] error = new boolean[1];
        final CountDownLatch lock = new CountDownLatch(1);
        GalleryPresenter presenter = new GalleryPresenter(new GalleryContract.GalleryView() {
            @Override
            public void photoUploaded(GalleryImage galleryImage) {

            }

            @Override
            public void errorUploadingPhoto() {

            }

            @Override
            public void onNewMedia(List<GalleryImage> list) {

            }

            @Override
            public void errorFetchingPhotos() {
                error[0] = true;
                lock.countDown();
            }
        }, dataManager);

        presenter.fetchPhotos(lastSeen);
        lock.await(2, TimeUnit.SECONDS);
        Assert.assertTrue(error[0]);
    }
}
