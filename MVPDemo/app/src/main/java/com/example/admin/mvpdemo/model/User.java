package com.example.admin.mvpdemo.model;

/**
 * Format
 * "gender":"male",
 * "name":{
 * "title":"mr",
 * "first":"rolf",
 * "last":"hegdal"
 * }
 */
public class User {
    String gender;
    UserName name;

    public String getGender() {
        return gender;
    }

    public UserName getName() {
        return name;
    }

    class UserName {
        String title;
        String first;
        String last;
    }
}
