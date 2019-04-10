package com.example.android.minireddit.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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

public class SavedPagerAdapter extends FragmentStatePagerAdapter {
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    /**
     * Constructor for new objects
     * @param fm    FragmentManager
     */
    public SavedPagerAdapter(FragmentManager fm) {
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
     */
    public void addFragment (Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

}
