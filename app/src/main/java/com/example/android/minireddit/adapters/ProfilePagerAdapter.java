package com.example.android.minireddit.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.android.minireddit.fragments.ProfileAboutFragment;
import com.example.android.minireddit.fragments.ProfileCommentsFragment;
import com.example.android.minireddit.fragments.ProfilePostsFragment;

/**
 * Created by karashily on 17/03/19.
 */

public class ProfilePagerAdapter extends FragmentStatePagerAdapter {

    private int mNoOfTabs;

    public ProfilePagerAdapter(FragmentManager fm, int noOfTabs) {
        super(fm);
        this.mNoOfTabs = noOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                ProfilePostsFragment postsFragment = new ProfilePostsFragment();
                return postsFragment;

            case 1:
                ProfileCommentsFragment commentsFragment = new ProfileCommentsFragment();
                return commentsFragment;

            case 2:
                ProfileAboutFragment aboutFragment = new ProfileAboutFragment();
                return aboutFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNoOfTabs;
    }
}
