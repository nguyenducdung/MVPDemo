package com.example.admin.mvpdemo.view.impl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.admin.mvpdemo.R;
import com.example.admin.mvpdemo.adapter.UserAdapter;
import com.example.admin.mvpdemo.model.User;
import com.example.admin.mvpdemo.presenter.MainPresenter;
import com.example.admin.mvpdemo.presenter.impl.MainPresenterImpl;
import com.example.admin.mvpdemo.view.MainView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView {

    MainPresenter mainPresenter;

    Button btnLoadData;

    UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainPresenter = new MainPresenterImpl(this);

        btnLoadData = findViewById(R.id.btnLoadData);
        btnLoadData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            mainPresenter.loadData();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mainPresenter.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mainPresenter.onStop();
    }

    @Override
    public void onLoadFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoadSuccess(List<User> users) {
        Toast.makeText(this, "onLoadSuccess", Toast.LENGTH_SHORT).show();
    }
}
