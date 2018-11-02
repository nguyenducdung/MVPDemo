package com.example.admin.mvpdemo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.mvpdemo.R;
import com.example.admin.mvpdemo.model.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter {
    private List<User> userList;
    private OnItemClickListener itemClickListener;
    private Context context;

    private final int VIEW_ITEM = 1;
    private final int VIEW_PROG = 0;

    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;
    private boolean loading;
    private OnLoadMoreListener onLoadMoreListener;


    public UserAdapter(List<User> userList, OnItemClickListener itemClickListener, Context context) {
        this.userList = userList;
        this.itemClickListener = itemClickListener;
        this.context = context;
    }

    public UserAdapter(List<User> users, RecyclerView recyclerView) {
        userList = users;

//        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
//
//            final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView
//                    .getLayoutManager();
//
//
//            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//                @Override
//                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                    super.onScrolled(recyclerView, dx, dy);
//                    totalItemCount = linearLayoutManager.getItemCount();
//                    lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
//                    if (!loading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
//                        // End has been reached
//                        // Do something
//                        if (onLoadMoreListener != null) {
//                            onLoadMoreListener.onLoadMore();
//                        }
//                        loading = true;
//                    }
//                }
//            });
//        }
    }

    public void clearData() {
        userList.clear();
        notifyDataSetChanged();
    }

    public void addData(List<User> users) {
        userList.addAll(users);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return userList.get(position) != null ? VIEW_ITEM : VIEW_PROG;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        final UserViewHolder viewHolder;
        if (i == VIEW_ITEM) {
            View view = inflater.inflate(R.layout.item_user, viewGroup, false);
            viewHolder = new UserViewHolder(view);
        } else {
            View view = inflater.inflate(R.layout.progressbar_user, viewGroup, false);
            viewHolder = new ProgressViewHolder(view);
        }

        /*view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClick(viewHolder.getAdapterPosition());
            }
        });*/
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof UserViewHolder) {
            User singleUser = (User) userList.get(i);
            ((UserViewHolder) viewHolder).tvGender.setText(singleUser.getGender());
            ((UserViewHolder) viewHolder).tvEmail.setText(singleUser.getEmail());
        } else {
            ((ProgressViewHolder) viewHolder).progressBar.setIndeterminate(true);
        }
    }

    public void setLoaded() {
        loading = false;
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        TextView tvGender;
        TextView tvEmail;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            tvGender = itemView.findViewById(R.id.tvGender);
            tvEmail = itemView.findViewById(R.id.tvEmail);
        }
    }

    private class ProgressViewHolder extends UserViewHolder {
        ProgressBar progressBar;

        public ProgressViewHolder(View view) {
            super(view);
            progressBar = view.findViewById(R.id.progressBar_user);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);

    }

    public interface OnLoadMoreListener {
        void onLoadMore();

    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    public void clear() {
        userList.clear();
        notifyDataSetChanged();
    }
}
