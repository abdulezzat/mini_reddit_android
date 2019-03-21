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

    private ProfilePostsFragment mPostsFragment;
    private ProfileCommentsFragment mCommentsFragment;
    private ProfileAboutFragment mAboutFragment;

    public ProfilePagerAdapter(FragmentManager fm, int noOfTabs) {
        super(fm);
        this.mNoOfTabs = noOfTabs;
        mPostsFragment = new ProfilePostsFragment();
        mCommentsFragment = new ProfileCommentsFragment();
        mAboutFragment = new ProfileAboutFragment();
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return mPostsFragment;

            case 1:
                return mCommentsFragment;

            case 2:
                return mAboutFragment;

            default:
                return null;
        }
    }

    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Posts";

            case 1:
                return "Comments";

            case 2:
                return "About";

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNoOfTabs;
    }
}
