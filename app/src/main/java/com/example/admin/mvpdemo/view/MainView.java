package com.example.admin.mvpdemo.view;

import com.example.admin.mvpdemo.model.User;

import java.util.List;

public interface MainView {

    void onLoadFailed(String message);

    void onLoadRefreshSuccess(List<User> users);

    void onLoadMoreSuccess(List<User> users);

}
