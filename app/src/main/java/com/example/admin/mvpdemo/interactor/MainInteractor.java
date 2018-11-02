package com.example.admin.mvpdemo.interactor;

import com.example.admin.mvpdemo.network.UserResponse;

import retrofit2.Callback;

public interface MainInteractor {
    void fetchUser(Callback<UserResponse> callback, int page);
}
