package com.coding.androidgallery.data.remote.api;


import com.coding.androidgallery.data.model.UploadResponse;
import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Mudassar Hussain on 5/26/2019.
 */
public interface GalleryApiService {

    @POST("fileupload.php")
    Observable<UploadResponse> uploadPhoto(@Body RequestBody requestBody);
}
