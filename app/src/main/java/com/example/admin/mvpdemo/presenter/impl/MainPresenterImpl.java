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
    private MainView mainView;
    private MainInteractor interactor;

    private int page = 0;
    private boolean isLoadingData;

    public MainPresenterImpl(MainView mainView) {
        this.mainView = mainView;
        interactor = new MainInteractorImpl();
    }

    @Override
    public void onStart() {
        // Do nothing
        refreshData();
    }

    @Override
    public void onStop() {
        // Do nothing
    }

    @Override
    public void refreshData() {
        if (isLoadingData) {
            return;
        }
        page = 1;
        loadData();
    }

    @Override
    public void loadMoreData() {
        if (isLoadingData) {
            return;
        }
        page++;
        loadData();
    }

    private void loadData() {
        isLoadingData = true;
        interactor.fetchUser(new Callback<UserResponse>() {
            @Override
            public void onResponse(@NonNull Call<UserResponse> call, @NonNull Response<UserResponse> response) {
                isLoadingData = false;
                UserResponse body = response.body();
                if (body == null) {
                    mainView.onLoadFailed("Load failed");
                    return;
                }
                List<User> users = body.getUsers();
                Log.e("MainPresenterImpl", "onResponse: " + users.size());
                if (page == 1) {
                    mainView.onLoadRefreshSuccess(users);
                } else if (page > 1) {
                    mainView.onLoadMoreSuccess(users);
                } else {
                    refreshData();
                }
            }

            @Override
            public void onFailure(@NonNull Call<UserResponse> call, @NonNull Throwable t) {
                isLoadingData = false;
                mainView.onLoadFailed(t.getMessage());
                page --;
            }
        }, page);
    }
}
