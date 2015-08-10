package com.cesarynga.blog.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cesarynga.blog.R;
import com.cesarynga.blog.model.Post;

import java.util.List;

/**
 * Created by CesarYnga on 8/10/15.
 */
public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    private List<Post> mPosts;

    public PostAdapter(List<Post> posts) {
        mPosts = posts;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_post, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.txtTitle.setText(mPosts.get(position).title);
    }

    @Override
    public int getItemCount() {
        return mPosts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView txtTitle;
        public ViewHolder(View itemView) {
            super(itemView);
            txtTitle = (TextView) itemView.findViewById(R.id.txtTitle);
        }
    }
}
