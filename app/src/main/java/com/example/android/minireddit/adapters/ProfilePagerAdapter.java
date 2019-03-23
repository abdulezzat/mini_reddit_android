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
 * <p>
 *     an adapter for the viewPager on the Profile that handles the fragments.
 * </p>
 */

public class ProfilePagerAdapter extends FragmentStatePagerAdapter {
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    /**
     * Constructor for new objects
     * @param fm    FragmentManager
     */
    public ProfilePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * a function that returns the fragment at a specific position
     *
     * @param position the position of the fragment desired
     * @return the desired fragment
     */
    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }

    /**
     * Function that adds a new fragment to the viewPager
     * @param fragment  the fragment to add.
     * @param title     the title of the added fragment.
     * @param user      the user that we're showing the profile of.
     */
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
