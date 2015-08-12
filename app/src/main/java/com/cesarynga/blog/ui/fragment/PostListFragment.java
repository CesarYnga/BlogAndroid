package com.cesarynga.blog.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cesarynga.blog.R;
import com.cesarynga.blog.model.Post;
import com.cesarynga.blog.ui.activity.PostDetailsActivity;
import com.cesarynga.blog.ui.adapter.PostAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CesarYnga on 8/10/15.
 */
public class PostListFragment extends Fragment implements PostAdapter.OnItemClickListener {

    private List<Post> mPosts = getDummyList();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_post_list, container, false);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        PostAdapter adapter = new PostAdapter(mPosts);
        adapter.setOnItemClickListener(this);
        recyclerView.setAdapter(adapter);
        return recyclerView;
    }

    private List<Post> getDummyList() {
        ArrayList<Post> dummyList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Post post = new Post();
            post.title = "Post " + i;
            post.description = "Lorem ipsum dolor sit amet, cu aperiam alienum vis. Ad cum quaeque consequat. Usu vitae contentiones an. An usu sumo intellegam assueverit. Petentium repudiandae neglegentur per ex, at sit saperet dissentias quaerendum, illum paulo oblique ex usu.";
            dummyList.add(post);
        }
        return dummyList;
    }

    @Override
    public void onItemClick(View v, int position) {
        Intent intent = new Intent(getActivity(), PostDetailsActivity.class);
        intent.putExtra(PostDetailsActivity.EXTRA_POST, mPosts.get(position));
        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                getActivity(),
                new Pair<>(v.findViewById(R.id.txtTitle), getString(R.string.shared_title)),
                new Pair<>(v.findViewById(R.id.txtAuthor), getString(R.string.shared_author)));
        ActivityCompat.startActivity(getActivity(), intent, optionsCompat.toBundle());
    }
}
