package com.coding.androidgallery.data.remote.api;


import com.coding.androidgallery.data.model.GalleryResponse;
import com.coding.androidgallery.data.model.UploadResponse;
import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Mudassar Hussain on 5/26/2019.
 */
public interface GalleryApiService {

    @POST("fileupload.php")
    Observable<UploadResponse> uploadPhoto(@Body RequestBody requestBody);

    @GET("fetchAll.php")
    Observable<GalleryResponse> fetchAll(@Query("userid") String userId, @Query("lastSeen") long lastSeen);
}
