package com.example.android.minireddit.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.android.minireddit.R;
import com.example.android.minireddit.adapters.ProfilePostsAdapter;
import com.example.android.minireddit.adapters.SavedPagerAdapter;
import com.example.android.minireddit.datastructure.Post;
import com.example.android.minireddit.networking.DependentClass;

import java.util.ArrayList;

/**
 * Created by karashily on 08/04/19.
 */

public class SavedFragment extends Fragment {

    private SavedPostsFragment mSavedPostsFragment;
    private SavedCommentsFragment mSavedCommentsFragment;

    public SavedFragment() { // empty constructor required.
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_saved, container, false);

        DependentClass.getInstance().ViewSavedLinks(getContext());

        mSavedPostsFragment = new SavedPostsFragment();
        mSavedCommentsFragment = new SavedCommentsFragment();

        SavedPagerAdapter savedPagerAdapter = new SavedPagerAdapter(getFragmentManager());
        savedPagerAdapter.addFragment(mSavedPostsFragment, "Posts");
        savedPagerAdapter.addFragment(mSavedCommentsFragment, "Comments");

        TabLayout tabLayout = (TabLayout) rootView.findViewById(R.id.tab_layout);

        ViewPager viewPager = (ViewPager) rootView.findViewById(R.id.profile_view_pager);

        viewPager.setAdapter(savedPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        // setting backButton Action
        ImageView backButton = (ImageView) rootView.findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AppCompatActivity act = (AppCompatActivity) getActivity();
                try {
                    act.onBackPressed();
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
        });

        return rootView;
    }
}
