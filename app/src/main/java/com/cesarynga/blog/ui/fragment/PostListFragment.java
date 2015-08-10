package com.cesarynga.blog.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cesarynga.blog.R;
import com.cesarynga.blog.model.Post;
import com.cesarynga.blog.ui.adapter.PostAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CesarYnga on 8/10/15.
 */
public class PostListFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_post_list, container, false);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(new PostAdapter(getDummyList()));
        return recyclerView;
    }

    private List<Post> getDummyList() {
        ArrayList<Post> dummyList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Post post = new Post();
            post.title = "Post " + i;
            dummyList.add(post);
        }
        return dummyList;
    }
}
