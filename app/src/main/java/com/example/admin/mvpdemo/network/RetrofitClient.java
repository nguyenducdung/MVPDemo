package com.example.admin.mvpdemo.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private UserApi userApi;
    private static RetrofitClient sInstance;

    private RetrofitClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://randomuser.me/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        userApi = retrofit.create(UserApi.class);
    }

    public static RetrofitClient getInstance() {
        if (null == sInstance) {
            sInstance = new RetrofitClient();
        }
        return sInstance;
    }

    public UserApi getUserApi() {
        return sInstance.userApi;
    }
}
