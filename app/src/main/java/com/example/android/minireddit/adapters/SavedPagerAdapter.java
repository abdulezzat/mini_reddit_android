package com.example.android.minireddit.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.android.minireddit.datastructure.User;
import com.example.android.minireddit.fragments.ProfileAboutFragment;
import com.example.android.minireddit.fragments.ProfileCommentsFragment;
import com.example.android.minireddit.fragments.ProfilePostsFragment;
import com.example.android.minireddit.fragments.SavedCommentsFragment;
import com.example.android.minireddit.fragments.SavedPostsFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by karashily on 08/04/19.
 */

public class SavedPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();
    private int numberOfPages = 2;
    private SavedPostsFragment mSavedPostsFragment;
    private SavedCommentsFragment mSavedCommentsFragment;

    /**
     * Constructor for new objects
     *
     * @param fm FragmentManager
     */
    public SavedPagerAdapter(FragmentManager fm) {
        super(fm);
        mSavedPostsFragment = new SavedPostsFragment();
        mSavedCommentsFragment = new SavedCommentsFragment();
    }

    /**
     * a function that returns the fragment at a specific position
     *
     * @param position the position of the fragment desired
     * @return the desired fragment
     */
    @Override
    public Fragment getItem(int position) {
        if(position == 0 ) return mSavedPostsFragment;
        else return mSavedCommentsFragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        if(position == 0 ) return "Posts";
        else return "Comments";
    }

    @Override
    public int getCount() {
        return numberOfPages;
    }

}
