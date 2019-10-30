package com.firstapp.hytripplan.api.user;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HaayAmazonRetrofitBuilder {
    public static HaayAmazonAPI getAPI(){
        return new Retrofit.Builder()
                .baseUrl("http://localhost:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient())
                .build().create(HaayAmazonAPI.class);
    }
}
