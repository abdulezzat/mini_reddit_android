package com.example.android.minireddit.adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.android.minireddit.fragments.PostFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter{
    private int numberOfPages = 2;
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        PostFragment postFragment;

        if(i==0) postFragment =new PostFragment(PostFragment.PostType.Home);
        else postFragment = new PostFragment(PostFragment.PostType.Popular);

        return postFragment;
    }

    @Override
    public int getCount() {
        return numberOfPages;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        if(position==0) return "Home";
        else return "Popular";
    }
}
