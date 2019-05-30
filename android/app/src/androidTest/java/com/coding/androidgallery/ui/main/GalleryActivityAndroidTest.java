package com.coding.androidgallery.ui.main;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.coding.androidgallery.App;
import com.coding.androidgallery.DaggerAndroidTestAppComponent;
import com.coding.androidgallery.R;
import com.coding.androidgallery.data.model.GalleryImage;
import com.coding.androidgallery.data.model.GalleryResponse;
import com.coding.androidgallery.data.model.UploadResponse;
import com.coding.androidgallery.modules.MockNetworkModule;
import com.coding.androidgallery.ui.main.adapter.GalleryPhotoAdapter;
import com.coding.androidgallery.util.android.AndroidHelper;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
import static android.content.res.Configuration.ORIENTATION_PORTRAIT;
import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasType;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.AllOf.allOf;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;

/**
 * Created by Mudassar Hussain on 6/2/2019.
 */
@RunWith(AndroidJUnit4.class)
public class GalleryActivityAndroidTest {
    @Rule
    public ActivityTestRule<GalleryActivity> testRule = new ActivityTestRule<>(GalleryActivity.class,true,false);

    GalleryActivity activity;

    //@Rule public GrantPermissionRule permissionRule = GrantPermissionRule.grant(Manifest.permission.READ_EXTERNAL_STORAGE);

    @Before
    public void setup(){
        App app = (App)InstrumentationRegistry.getTargetContext().getApplicationContext();
        app.replaceAppComponent(DaggerAndroidTestAppComponent.builder().mockNetworkModule(mockResponse()).build());
        testRule.launchActivity(new Intent());
        activity = testRule.getActivity();
        activity.hasStoragePermission = true;//I am setting is because its not working on my phone with permissionRule
    }

    @After
    public void tearDown(){
        App app = (App)InstrumentationRegistry.getTargetContext().getApplicationContext();
        app.clearAppComponent();
    }

    private MockNetworkModule mockResponse() {
        MockNetworkModule mock = new MockNetworkModule(mockUploadResponse(),mockGalleryResponse());
        return mock;
    }

    private UploadResponse mockUploadResponse(){
        UploadResponse response = new UploadResponse(GalleryResponse.SUCCESS_CODE,"Mock Response");
        return response;
    }

    private GalleryResponse mockGalleryResponse(){
        GalleryResponse response = new GalleryResponse(GalleryResponse.SUCCESS_CODE,"Mock Response");
        response.setHasMore(false);
        List<GalleryImage> list = new ArrayList<>();
        list.add(mockObject(1));
        list.add(mockObject(2));
        response.setGalleryList(list);
        return response;
    }

    private GalleryImage mockObject(long id){
        return new GalleryImage(id,"test.png","05/05/1989","{}");
    }


    @Test
    public void testPickFromGallery_ShouldReturnResultOk(){
        Intents.init();
        Matcher<Intent> expectedIntent = allOf(hasAction(Intent.ACTION_PICK),
                hasType("image/*"));
        Intent resultIntent = new Intent(Intent.ACTION_PICK);
        resultIntent.setData(Uri.EMPTY);
        Instrumentation.ActivityResult result = new Instrumentation.ActivityResult(Activity.RESULT_OK, resultIntent);

        intending(expectedIntent).respondWith(result);

        onView(withId(R.id.ic_pick_from_gallery)).perform(click());
        intended(expectedIntent);
        Intents.release();
    }

    @Test
    public void testTakePictureCamera_ShouldReturnResultOk(){
        Intents.init();
        Bitmap testBitmap = Bitmap.createBitmap(30, 30, Bitmap.Config.ARGB_8888);
        Intent resultIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        resultIntent.putExtra("data", testBitmap);
        Instrumentation.ActivityResult result = new Instrumentation.ActivityResult(Activity.RESULT_OK, resultIntent);

        String targetPackage = resultIntent.resolveActivity(InstrumentationRegistry.getTargetContext().getPackageManager()).getPackageName();

        intending(toPackage(targetPackage)).respondWith(result);

        onView(withId(R.id.ic_camera)).perform(click());
        intended(toPackage(targetPackage));
        Intents.release();
    }

    @Test
    public void testListGrid_ShouldChangeListColumns(){
        int orientation = activity.getRequestedOrientation();
        int newOrientation = orientation == ORIENTATION_PORTRAIT ? SCREEN_ORIENTATION_LANDSCAPE : SCREEN_ORIENTATION_PORTRAIT;
        activity.setRequestedOrientation(newOrientation);
        getInstrumentation().waitForIdleSync();
        int columnCount = AndroidHelper.columnCount(activity,activity.getResources().getDimension(R.dimen.photo_list_item_size));
        Assert.assertTrue(columnCount > 0);
        orientation = activity.getRequestedOrientation();
        newOrientation = orientation == ORIENTATION_PORTRAIT ? SCREEN_ORIENTATION_LANDSCAPE : SCREEN_ORIENTATION_PORTRAIT;
        activity.setRequestedOrientation(newOrientation);
        getInstrumentation().waitForIdleSync();
        int newCount = AndroidHelper.columnCount(activity,activity.getResources().getDimension(R.dimen.photo_list_item_size));
        Assert.assertTrue(newCount > 0);
        Assert.assertNotEquals(columnCount,newCount);
    }


    @Test
    public void testPhotoList_ShouldHaveMockData(){

        onView(withId(R.id.photoList)).check(matches(compareObjectAt(0,mockObject(1))));
        onView(withId(R.id.photoList)).check(matches(not(compareObjectAt(0,mockObject(2)))));
        onView(withId(R.id.photoList)).check(matches(compareList(mockGalleryResponse().getGalleryList())));

        onView(withId(R.id.photoList)).check(matches(itemCount(2)));
    }

    @Test
    public void testPhotoUpload_ShouldHaveMockData() throws InterruptedException {
        Bitmap testBitmap = Bitmap.createBitmap(30, 30, Bitmap.Config.ARGB_8888);
        activity.upload(testBitmap);
        Assert.assertTrue(activity.isUploading());
        Thread.sleep(2000);
        onView(withId(R.id.photoList)).check(matches(itemCount(3)));
    }

    private static Matcher<View> compareObjectAt(final int position, final GalleryImage galleryImage) {
        return new TypeSafeMatcher<View>() {

            @Override
            public void describeTo(Description description) {

            }

            @Override
            protected boolean matchesSafely(View item) {
                RecyclerView photoList = (RecyclerView) item;
                GalleryPhotoAdapter photoAdapter = (GalleryPhotoAdapter) photoList.getAdapter();
                return photoAdapter.getItem(position).equals(galleryImage);
            }
        };
    }

    private static Matcher<View> compareList(final List<GalleryImage> mockList) {
        return new TypeSafeMatcher<View>() {

            @Override
            public void describeTo(Description description) {

            }

            @Override
            protected boolean matchesSafely(View item) {
                RecyclerView photoList = (RecyclerView) item;
                GalleryPhotoAdapter photoAdapter = (GalleryPhotoAdapter) photoList.getAdapter();
                return photoAdapter.getItems().equals(mockList);
            }
        };
    }

    private static Matcher<View> itemCount(final int count) {
        return new TypeSafeMatcher<View>() {

            @Override
            public void describeTo(Description description) {

            }

            @Override
            protected boolean matchesSafely(View item) {
                RecyclerView photoList = (RecyclerView) item;
                GalleryPhotoAdapter photoAdapter = (GalleryPhotoAdapter) photoList.getAdapter();
                return photoAdapter.getItemCount() == count;
            }
        };
    }
}
