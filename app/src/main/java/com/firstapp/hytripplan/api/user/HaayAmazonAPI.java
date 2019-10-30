package com.firstapp.hytripplan.api.user;

import com.firstapp.hytripplan.api.vo.tripdata.TripData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface HaayAmazonAPI {
    @GET("user")
    Call<User> searchKeyword();
}
