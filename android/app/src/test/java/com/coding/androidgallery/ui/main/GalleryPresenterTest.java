package com.coding.androidgallery.ui.main;

import com.coding.androidgallery.data.GalleryDataManager;
import com.coding.androidgallery.data.model.UploadResponse;
import com.coding.androidgallery.data.model.UploadResponse;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Mudassar Hussain on 5/27/2019.
 */
public class GalleryPresenterTest {

    GalleryPresenter presenter;

    @Mock
    GalleryContract.GalleryView view;

    @Mock
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
        presenter = new GalleryPresenter(view,dataManager);
    }

    @Test
    public void uploadPhoto_WithFilePath_ShouldUploadPhoto_ShowPhotoUploaded() {
        String imageFilePath = "test.png";
        UploadResponse response = mock(UploadResponse.class);
        when(dataManager.uploadPhoto(imageFilePath)).thenReturn(Observable.just(response));
        presenter.uploadPhoto(imageFilePath);
        verify(view).photoUploaded();
    }

    @Test
    public void uploadPhoto_WithFilePath_ShouldThrowException_ShowError() {
        String imageFilePath = "test.png";
        when(dataManager.uploadPhoto(imageFilePath)).thenReturn(Observable.<UploadResponse>error(new Exception("Test Error")));
        presenter.uploadPhoto(imageFilePath);
        verify(view).errorUploadingPhoto();
    }

    @Test
    public void uploadPhoto_WithFilePath_DeAttach() {
        String imageFilePath = "test.png";
        UploadResponse response = mock(UploadResponse.class);
        when(dataManager.uploadPhoto(imageFilePath)).thenReturn(Observable.just(response));
        presenter.uploadPhoto(imageFilePath);
        verify(view).photoUploaded();
        presenter.destroyView();
        Assert.assertTrue(presenter.getCompositeDisposable().isDisposed());
        Assert.assertNull(presenter.getView());
    }

}
