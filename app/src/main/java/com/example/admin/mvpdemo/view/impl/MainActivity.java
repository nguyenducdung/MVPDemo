package com.example.admin.mvpdemo.view.impl;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.mvpdemo.R;
import com.example.admin.mvpdemo.adapter.UserAdapter;
import com.example.admin.mvpdemo.model.User;
import com.example.admin.mvpdemo.presenter.MainPresenter;
import com.example.admin.mvpdemo.presenter.impl.MainPresenterImpl;
import com.example.admin.mvpdemo.view.MainView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView {

    MainPresenter mainPresenter;

    UserAdapter userAdapter;

    private RecyclerView userRecyclerView;

    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        mainPresenter = new MainPresenterImpl(this);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                userAdapter.clear();
                mainPresenter.refreshData();
            }
        });

        swipeRefreshLayout.setColorSchemeColors(
                getResources().getColor(android.R.color.holo_blue_bright),
                getResources().getColor(android.R.color.holo_green_light),
                getResources().getColor(android.R.color.holo_orange_light),
                getResources().getColor(android.R.color.holo_red_light)
        );

        userAdapter = new UserAdapter(new ArrayList<User>(), userRecyclerView);
        userRecyclerView.setAdapter(userAdapter);
//        userAdapter.setOnLoadMoreListener(new UserAdapter.OnLoadMoreListener() {
//            @Override
//            public void onLoadMore() {
//                /*page++;
//                mainPresenter.loadData(page);*/
//                Toast.makeText(MainActivity.this, "hihi", Toast.LENGTH_SHORT).show();
//            }
//        });

        userRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1)) {
                    mainPresenter.loadMoreData();
                }
            }
        });


    }

    private void initView() {
        userRecyclerView = findViewById(R.id.rcUsers);
        RecyclerView.LayoutManager userLayoutManager = new LinearLayoutManager(getApplicationContext());
        userRecyclerView.setLayoutManager(userLayoutManager);
        userRecyclerView.addItemDecoration(new DividerItemDecoration(this.getApplicationContext(), LinearLayout.VERTICAL));
        swipeRefreshLayout = findViewById(R.id.swipeRefresh);
        TextView tvEmptyView = findViewById(R.id.empty_view);
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
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onLoadRefreshSuccess(List<User> users) {
        swipeRefreshLayout.setRefreshing(false);
        userAdapter.clearData();
        userAdapter.addData(users);
    }

    @Override
    public void onLoadMoreSuccess(List<User> users) {
        swipeRefreshLayout.setRefreshing(false);
        userAdapter.addData(users);
    }

}
