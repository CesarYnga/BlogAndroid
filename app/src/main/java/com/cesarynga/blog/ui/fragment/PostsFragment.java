package com.cesarynga.blog.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cesarynga.blog.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CesarYnga on 8/10/15.
 */
public class PostsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_posts, container, false);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ActionBar ab =((AppCompatActivity) getActivity()).getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        setUpViewPager(viewPager);
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }

    private void setUpViewPager(ViewPager viewPager) {
        PostPagerAdapter adapter = new PostPagerAdapter(getFragmentManager());
        adapter.addFragment(new PostListFragment(), "All");
        adapter.addFragment(new PostListFragment(), "My Posts");
        adapter.addFragment(new PostListFragment(), "My Favorites");
        viewPager.setAdapter(adapter);
    }

    private static class PostPagerAdapter extends FragmentPagerAdapter {

        private final List<String> mTitles = new ArrayList<>();
        private final List<Fragment> mFragments = new ArrayList<>();

        public PostPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles.get(position);
        }
    }
}
