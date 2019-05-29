package com.coding.androidgallery.ui.main;

import com.coding.androidgallery.DaggerTestAppComponent;
import com.coding.androidgallery.TestAppComponent;
import com.coding.androidgallery.data.GalleryDataManager;
import com.coding.androidgallery.data.model.UploadResponse;
import com.coding.androidgallery.modules.MockNetworkModule;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.Mockito.mock;

/**
 * Created by Mudassar Hussain on 5/30/2019.
 */
public class GalleryActivityPresenterTest {
    @Inject
    GalleryDataManager dataManager;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(new Function<Callable<Scheduler>, Scheduler>() {
            @Override
            public Scheduler apply(Callable<Scheduler> schedulerCallable) throws Exception {
                return Schedulers.trampoline();
            }
        });
    }

    private void setupDI(UploadResponse mock){
        TestAppComponent appComponent = DaggerTestAppComponent.builder().mockNetworkModule(new MockNetworkModule(mock)).build();
        appComponent.inject(this);
    }

    @Test
    public void uploadPhoto_WithFilePath_ShouldShowUploadedPhoto() throws InterruptedException {
        String imageFilePath = "test.png";
        final boolean[] status = new boolean[1];
        setupDI(mock(UploadResponse.class));
        final CountDownLatch lock = new CountDownLatch(1);
        GalleryPresenter presenter = new GalleryPresenter(new GalleryContract.GalleryView() {
            @Override
            public void photoUploaded() {
                status[0] = true;
                lock.countDown();
            }

            @Override
            public void errorUploadingPhoto() {

            }
        }, dataManager);

        presenter.uploadPhoto(imageFilePath);
        lock.await(2, TimeUnit.SECONDS);
        Assert.assertTrue(status[0]);
    }

    @Test
    public void uploadPhoto_WithFilePath_ShouldShowError() throws InterruptedException {
        String imageFilePath = "test.png";
        setupDI(null);
        final boolean[] error = new boolean[1];
        final CountDownLatch lock = new CountDownLatch(1);
        GalleryPresenter presenter = new GalleryPresenter(new GalleryContract.GalleryView() {
            @Override
            public void photoUploaded() {

            }

            @Override
            public void errorUploadingPhoto() {
                error[0] = true;
                lock.countDown();
            }
        }, dataManager);

        presenter.uploadPhoto(imageFilePath);
        lock.await(2, TimeUnit.SECONDS);
        Assert.assertTrue(error[0]);
    }
}
