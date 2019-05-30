package com.coding.androidgallery.util.android;

import android.graphics.Bitmap;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.coding.androidgallery.ui.main.GalleryActivity;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

/**
 * Created by Mudassar Hussain on 5/30/2019.
 */
@RunWith(AndroidJUnit4.class)
public class FileHelperTest {

    @Rule
    public ActivityTestRule<GalleryActivity> testRule = new ActivityTestRule<>(GalleryActivity.class);

    @Test
    public void saveBitmapToFile_ShouldWriteToFile() throws IOException {
        Bitmap testBitmap = Bitmap.createBitmap(30, 30, Bitmap.Config.ARGB_8888);
        String path = FileHelper.saveBitmapToFile(testRule.getActivity(),testBitmap);
        Assert.assertNotNull(path);
    }
}
