package com.example.admin.mvpdemo.interactor.impl;

import com.example.admin.mvpdemo.interactor.MainInteractor;
import com.example.admin.mvpdemo.network.RetrofitClient;
import com.example.admin.mvpdemo.network.UserApi;
import com.example.admin.mvpdemo.network.UserResponse;

import retrofit2.Callback;

public class MainInteractorImpl implements MainInteractor {


    @Override
    public void fetchUser(Callback<UserResponse> callback) {
        UserApi userApi = RetrofitClient.getInstance().getUserApi();
        userApi.fetchUser(10).enqueue(callback);

    }
}
