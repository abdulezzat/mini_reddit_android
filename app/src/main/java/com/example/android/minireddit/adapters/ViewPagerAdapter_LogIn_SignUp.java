package com.example.android.minireddit.adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.android.minireddit.fragments.LogInFragment;
import com.example.android.minireddit.fragments.PostFragment;
import com.example.android.minireddit.fragments.SignUpFragment;

public class ViewPagerAdapter_LogIn_SignUp extends FragmentPagerAdapter {
    private int numberOfPages = 2;
    SignUpFragment mSignUpFragment;
    LogInFragment mLogInFragment;


    public ViewPagerAdapter_LogIn_SignUp(FragmentManager fm) {
        super(fm);
        mSignUpFragment = new SignUpFragment();
        mLogInFragment = new LogInFragment();
    }

    @Override
    public Fragment getItem(int i) {


        if (i == 0) return mLogInFragment;
        else return mSignUpFragment;

    }

    @Override
    public int getCount() {
        return numberOfPages;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        if (position == 0) return "LogIn";
        else return "SingUp";
    }
}
