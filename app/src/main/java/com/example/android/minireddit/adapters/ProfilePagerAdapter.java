package com.example.android.minireddit.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.android.minireddit.datastructure.User;
import com.example.android.minireddit.fragments.ProfileAboutFragment;
import com.example.android.minireddit.fragments.ProfileCommentsFragment;
import com.example.android.minireddit.fragments.ProfilePostsFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by karashily on 17/03/19.
 */

public class ProfilePagerAdapter extends FragmentStatePagerAdapter {
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    public ProfilePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }

    public void addFragment (Fragment fragment, String title, User user) {
        if(fragment instanceof ProfilePostsFragment) {
            ProfilePostsFragment profileFragment=(ProfilePostsFragment) fragment;
            profileFragment.setUser(user);
        } else if(fragment instanceof ProfileCommentsFragment) {
            ProfileCommentsFragment profileFragment=(ProfileCommentsFragment) fragment;
            profileFragment.setUser(user);
        } else if(fragment instanceof ProfileAboutFragment) {
            ProfileAboutFragment profileFragment=(ProfileAboutFragment) fragment;
            profileFragment.setUser(user);
        }
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }
}
