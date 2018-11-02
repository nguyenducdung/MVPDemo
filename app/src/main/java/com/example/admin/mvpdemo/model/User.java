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
    String email;


    public String getGender() {
        return gender;
    }


    public String getEmail() {
        return email;
    }
}
