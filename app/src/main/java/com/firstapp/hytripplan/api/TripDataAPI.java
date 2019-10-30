package com.firstapp.hytripplan.api;

import com.firstapp.hytripplan.api.vo.tripdata.TripData;
import com.firstapp.hytripplan.api.vo.tripimage.TripImage;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface TripDataAPI {
    /*http://api.visitkorea.or.kr/openapi/service/rest/
    obj : []
    obj : {}
    KorService/searchKeyword*/

    @Headers({"Accept: application/json"})
    @GET("KorService/searchKeyword")
    Call<TripData> searchKeyword(@Query("MobileOS") String mobileOS
                    , @Query("MobileApp") String mobileApp
                    , @Query(value = "ServiceKey", encoded = true) String serviceKey
                    , @Query("keyword") String keyword
                    , @Query("_type") String type);

    @GET("KorService/detailImage")
    Call<TripImage> detailImage(@Query("MobileOS") String mobileOS
            , @Query("MobileApp") String mobileApp
            , @Query(value = "ServiceKey", encoded = true) String serviceKey
            , @Query("contentId") String contentId
            , @Query("imageYN") String imageYN
            , @Query("subImageYN") String subImageYN
            , @Query("_type") String type);
}
