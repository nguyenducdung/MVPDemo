package com.example.admin.mvpdemo.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UserApi {

    @GET("api")
    Call<UserResponse> fetchUser(@Query("results") int result,
                                 @Query("page") int page);

}
