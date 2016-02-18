package com.river.ilikeit.photo;

import com.river.ilikeit.main.photo.PhotoInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PhotoApi {

    @GET("get/pins")
    List<PhotoInfo> getPins();
    Call<List<PhotoInfo>> loadPins();
}
