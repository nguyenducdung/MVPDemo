package com.example.admin.mvpdemo.presenter.impl;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.admin.mvpdemo.interactor.MainInteractor;
import com.example.admin.mvpdemo.interactor.impl.MainInteractorImpl;
import com.example.admin.mvpdemo.model.User;
import com.example.admin.mvpdemo.network.UserResponse;
import com.example.admin.mvpdemo.presenter.MainPresenter;
import com.example.admin.mvpdemo.view.MainView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenterImpl implements MainPresenter {
    MainView mainView;
    MainInteractor interactor;

    public MainPresenterImpl(MainView mainView) {
        this.mainView = mainView;
        interactor = new MainInteractorImpl();
    }

    @Override
    public void onStart() {
        // Do nothing
    }

    @Override
    public void onStop() {
        // Do nothing
    }

    @Override
    public void loadData() {
        interactor.fetchUser(new Callback<UserResponse>() {
            @Override
            public void onResponse(@NonNull Call<UserResponse> call, @NonNull Response<UserResponse> response) {
                UserResponse body = response.body();
                List<User> users = body.getUsers();
                Log.e("MainPresenterImpl", "onResponse: " + users.size());
                mainView.onLoadSuccess(users);
            }

            @Override
            public void onFailure(@NonNull Call<UserResponse> call, @NonNull Throwable t) {
                mainView.onLoadFailed(t.getMessage());
            }
        });
    }
}
