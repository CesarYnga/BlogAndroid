package com.cesarynga.blog.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.TextView;

import com.cesarynga.blog.R;
import com.cesarynga.blog.model.Post;

/**
 * Created by CesarYnga on 8/12/15.
 */
public class PostDetailsActivity extends AppCompatActivity {

    public static final String EXTRA_POST = "post";
    public static final String EXTRA_EDITABLE = "editable";

    private Post mPost;
    private boolean mEditable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_details);
        mPost = getIntent().getParcelableExtra(EXTRA_POST);
        mEditable = getIntent().getBooleanExtra(EXTRA_EDITABLE, false);
        TextView txtTitle = (TextView) findViewById(R.id.txtTitle);
        TextView txtAuthor = (TextView) findViewById(R.id.txtAuthor);
        TextView txtDateTime = (TextView) findViewById(R.id.txtDateTime);
        TextView txtDescription = (TextView) findViewById(R.id.txtDescription);

        txtTitle.setText(mPost.title);
        txtAuthor.setText("by César Ynga");
        txtDateTime.setText("Published on Aug 12, 2015");
        txtDescription.setText(mPost.description);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (mEditable) {
            // TODO: add edit action
        }
        return super.onCreateOptionsMenu(menu);
    }
}
