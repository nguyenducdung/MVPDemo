package com.example.admin.mvpdemo.network;

import com.example.admin.mvpdemo.model.User;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserResponse {
    @SerializedName("results")
    public List<User> users;

    public List<User> getUsers() {
        return users;
    }
}
